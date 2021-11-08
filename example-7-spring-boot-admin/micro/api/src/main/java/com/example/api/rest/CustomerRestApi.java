package com.example.api.rest;

import com.example.api.pojo.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface CustomerRestApi {

    @GetMapping("/customers/{id}")
    Optional<Customer> findCustomer(@PathVariable int id);

}
