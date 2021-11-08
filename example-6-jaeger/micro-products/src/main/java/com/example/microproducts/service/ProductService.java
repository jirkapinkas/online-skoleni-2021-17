package com.example.microproducts.service;

import com.example.microproducts.pojo.Product;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private List<Product> products;

    @PostConstruct
    public void init() {
        products = Arrays.asList(
                new Product(1, "Stuff"),
                new Product(2, "Other stuff"),
                new Product(3, "YAGNI stuff")
        );
    }

    public List<Product> findAll() {
        return products;
    }

}
