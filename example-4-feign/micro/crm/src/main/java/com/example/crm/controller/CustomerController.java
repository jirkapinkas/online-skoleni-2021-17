package com.example.crm.controller;

import com.example.api.pojo.Customer;
import com.example.api.rest.CustomerRestApi;
import com.example.crm.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController /* implements CustomerRestApi */ {

    @Autowired
    private CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/customers/{id}")
    Optional<Customer> findCustomer(@PathVariable int id) {
        log.info("Called CustomerController::customer, customerId = {}", id);
        return customerService.findOne(id);
    }

    /*
    @Override
    public Optional<Customer> findCustomer(int id) {
        log.info("Called CustomerController::customer, customerId = {}", id);
        return customerService.findOne(id);
    }
     */
}
