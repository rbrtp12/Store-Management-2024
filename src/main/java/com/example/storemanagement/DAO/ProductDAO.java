package com.example.storemanagement.DAO;


import com.example.storemanagement.model.Product;

import java.util.List;

public interface ProductDAO {
    Product addProduct(Product product);
    Product findProduct(Long id);
    List<Product> getAllProducts();
    Product changePrice(Long id, float newPrice);
    void deleteProduct(Long id);
}

