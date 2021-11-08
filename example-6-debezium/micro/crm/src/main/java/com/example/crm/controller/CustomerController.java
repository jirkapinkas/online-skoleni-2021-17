package com.example.crm.controller;

import com.example.crm.entity.Customer;
import com.example.crm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers/{id}")
    public Optional<Customer> findCustomer(@PathVariable int id) {
        log.info("Called CustomerController::customer, customerId = {}", id);
        return customerService.findOne(id);
    }

    @PostMapping("/customers")
    public Customer insert(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @DeleteMapping("/customers/{id}")
    public void delete(@PathVariable int id) {
        customerService.delete(id);
    }

}
