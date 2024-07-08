package com.example.storemanagement.service;

import com.example.storemanagement.model.Product;
import com.example.storemanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> findProduct(Long id){
        return productRepository.findById(id);
    }

    public Product changePrice(Long id, double newPrice){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setPrice(newPrice);
            return productRepository.save(product);
        }
        return null;
    }
}
