package com.example.storemanagement.service;

import com.example.storemanagement.DAO.ProductDAO;
import com.example.storemanagement.DAO.ProductDAOImpl;
import com.example.storemanagement.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public Product addProduct(Product product);

    public Product findProduct(Long id);

    public List<Product> getAllProducts();

    public Product changePrice(Long id, Double newPrice);

    public void deleteProduct(Long id);
}
