package com.example.storemanagement.controller;

import com.example.storemanagement.model.Product;
import com.example.storemanagement.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        logger.info("Received request to add product: {}", product.getName());
        Product createdProduct = productService.addProduct(product);
        logger.info("Product added successfully: {}", createdProduct);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Long id) {
        logger.info("Received request to find product with ID: {}", id);
        Product product = productService.findProduct(id);
        if (product != null) {
            logger.info("Product found: {}", product);
            return ResponseEntity.ok(product);
        }
        logger.warn("Product with ID: {} not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        logger.info("Received request to get all products");
        List<Product> products = productService.getAllProducts();
        if (products != null && !products.isEmpty()) {
            logger.info("Products retrieved successfully: {}", products);
            return ResponseEntity.ok(products);
        }
        logger.warn("No products found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> changePrice(@PathVariable Long id, @RequestParam Double newPrice) {
        logger.info("Received request to change price for product with ID: {} to {}", id, newPrice);
        Product product = productService.changePrice(id, newPrice);
        if (product != null) {
            logger.info("Price changed successfully for product with ID: {}", id);
            return ResponseEntity.ok(product);
        }
        logger.warn("Product with ID: {} not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        logger.info("Received request to delete product with ID: {} to {}", id);
         productService.deleteProduct(id);
         Product deletedProduct = productService.findProduct(id);
        if (deletedProduct == null) {
            logger.info("Product deleted successfully for product with ID: {}", id);
            return ResponseEntity.ok(deletedProduct);
        }
        logger.warn("Product with ID: {} not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
