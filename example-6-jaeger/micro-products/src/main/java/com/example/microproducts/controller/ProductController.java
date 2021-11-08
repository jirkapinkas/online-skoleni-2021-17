package com.example.microproducts.controller;

import com.example.microproducts.pojo.Product;
import com.example.microproducts.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/products")
    public List<Product> products() {
        log.info("Called ProductController::products");
        return productService.findAll();
    }

}
