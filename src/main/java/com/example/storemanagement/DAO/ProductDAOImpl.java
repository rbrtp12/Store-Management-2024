package com.example.storemanagement.DAO;


import com.example.storemanagement.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl implements ProductDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    @Override
    public Product addProduct(Product product) {
        String sql = "INSERT INTO PRODUCTS (NAME, PRICE) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                product.setId(rs.getLong(1));
            }
            logger.info("Product added successfully: {}", product);

        } catch (SQLException e) {
            logger.error("Error adding product: {}", product, e);
        }
        return product;
    }

    @Override
    public Product findProduct(Long id) {
        String sql = "SELECT * FROM PRODUCTS WHERE ID = ?";
        Product product = null;
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setId(rs.getLong("ID"));
                product.setName(rs.getString("NAME"));
                product.setPrice(rs.getDouble("PRICE"));
            }
            logger.info("Product found with ID {}: {}", id, product);
        } catch (SQLException e) {
            logger.error("Error finding product with ID: {}", id, e);
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM PRODUCTS";
        List<Product> products = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("ID"));
                product.setName(rs.getString("NAME"));
                product.setPrice(rs.getDouble("PRICE"));
                products.add(product);
            }
            logger.info("All products retrieved: {}", products);
        } catch (SQLException e) {
            logger.error("Error retrieving all products", e);
        }
        return products;
    }

    @Override
    public Product changePrice(Long id, Double newPrice) {
            String sql = "UPDATE PRODUCTS SET PRICE = ? WHERE ID = ?";
            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setDouble(1, newPrice);
                pstmt.setLong(2, id);
                pstmt.executeUpdate();
                logger.info("Price changed for product with ID {}: new price {}", id, newPrice);
            } catch (SQLException e) {
                logger.error("Error changing price for product with ID: {}", id, e);
            }
        return findProduct(id);
    }

    @Override
    public void deleteProduct(Long id) {
        String sql = "DELETE FROM PRODUCTS WHERE ID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            logger.info("Product deleted with ID: {}", id);
        } catch (SQLException e) {
            logger.error("Error deleting product with ID: {}", id, e);
        }
    }
}

