package com.example.storemanagement.service;

import com.example.storemanagement.DAO.ProductDAO;
import com.example.storemanagement.DAO.ProductDAOImpl;
import com.example.storemanagement.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private final ProductDAO productDAO = new ProductDAOImpl();
    public Product addProduct(Product product){
        return productDAO.addProduct(product);
    }

    public Product findProduct(Long id){
        return productDAO.findProduct(id);
    }

    public List<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }

    public Product changePrice(Long id, Double newPrice){
        return productDAO.changePrice(id, newPrice);
    }
}
