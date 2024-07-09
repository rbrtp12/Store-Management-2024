package com.example.storemanagement.controller;


import com.example.storemanagement.model.Product;
import com.example.storemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/view-products")
    public String viewProducts(@RequestParam(required = false) String sortBy, Model model) {
        List<Product> products = productService.getAllProducts();
        if("name".equalsIgnoreCase(sortBy)){
            products.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
        }
        else if ("price".equalsIgnoreCase(sortBy)){
            products.sort((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
        }
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/new-products")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "new_product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/view-products";
    }

    @GetMapping("/edit-product/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.findProduct(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "edit_product";
        } else {
            return "redirect:/view-products";
        }
    }

    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute Product product) {
        productService.changePrice(product.getId(), product.getPrice());
        return "redirect:/view-products";
    }
}
