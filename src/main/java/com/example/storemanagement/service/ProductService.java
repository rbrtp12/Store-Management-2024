package com.example.storemanagement.service;

import com.example.storemanagement.DAO.ProductDAO;
import com.example.storemanagement.DAO.ProductDAOImpl;
import com.example.storemanagement.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDAO productDAO = new ProductDAOImpl();
    public Product addProduct(Product product){
        return productDAO.addProduct(product);
    }

    public Product findProduct(Long id){
        return productDAO.findProduct(id);
    }

    public Product changePrice(Long id, float newPrice){
        return productDAO.changePrice(id, newPrice);
    }
}
