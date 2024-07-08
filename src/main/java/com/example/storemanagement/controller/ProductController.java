package com.example.storemanagement.controller;

import com.example.storemanagement.model.Product;
import com.example.storemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Long id) {
        Product product = productService.findProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<Product> changePrice(@PathVariable Long id, @RequestParam float newPrice) {
        Product product = productService.changePrice(id, newPrice);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
