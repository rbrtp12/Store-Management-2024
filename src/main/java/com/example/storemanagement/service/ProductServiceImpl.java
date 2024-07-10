package com.example.storemanagement.service;

import com.example.storemanagement.DAO.ProductDAO;
import com.example.storemanagement.DAO.ProductDAOImpl;
import com.example.storemanagement.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductDAO productDAO = new ProductDAOImpl();
    @Override
    public Product addProduct(Product product) {
        return productDAO.addProduct(product);
    }
    @Override
    public Product findProduct(Long id) {
        return productDAO.findProduct(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public Product changePrice(Long id, Double newPrice) {
        return productDAO.changePrice(id, newPrice);
    }

    @Override
    public void deleteProduct(Long id) {
        productDAO.deleteProduct(id);
    }
}
