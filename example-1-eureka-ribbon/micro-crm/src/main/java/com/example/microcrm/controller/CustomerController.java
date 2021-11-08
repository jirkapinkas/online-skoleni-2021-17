package com.example.microcrm.controller;

import com.example.microcrm.pojo.Customer;
import com.example.microcrm.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/customers/{id}")
    public Optional<Customer> customer(@PathVariable int id) {
        log.info("Called CustomerController::customer, customerId = {}", id);
        return customerService.findOne(id);
    }

}
