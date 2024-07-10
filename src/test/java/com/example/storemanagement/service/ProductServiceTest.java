package com.example.storemanagement.service;

import com.example.storemanagement.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0);

        when(productService.addProduct(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.addProduct(product);

        assertNotNull(createdProduct);
        assertEquals(1L, createdProduct.getId());
        assertEquals("Test Product", createdProduct.getName());

        assertEquals(100.0, createdProduct.getPrice());

        verify(productService, times(1)).addProduct(any(Product.class));
    }

    @Test
    void findProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0);

        when(productService.findProduct(1L)).thenReturn(product);

        Product foundProduct = productService.findProduct(1L);

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        assertEquals("Test Product", foundProduct.getName());
        assertEquals(100.0, foundProduct.getPrice());

        verify(productService, times(1)).findProduct(1L);
    }

    @Test
    void getAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(100.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(200.0);

        List<Product> products = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);

        List<Product> foundProducts = productService.getAllProducts();

        assertNotNull(foundProducts);
        assertEquals(2, foundProducts.size());

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void changePrice() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(150.0);

        when(productService.changePrice(1L, 150.0)).thenReturn(product);

        Product updatedProduct = productService.changePrice(1L, 150.0);

        assertNotNull(updatedProduct);
        assertEquals(1L, updatedProduct.getId());
        assertEquals("Test Product", updatedProduct.getName());
        assertEquals(150.0, updatedProduct.getPrice());

        verify(productService, times(1)).changePrice(1L, 150.0);
    }

    @Test
    void deleteProduct() {
        doNothing().when(productService).deleteProduct(1L);

        productService.deleteProduct(1L);

        verify(productService, times(1)).deleteProduct(1L);
    }
}