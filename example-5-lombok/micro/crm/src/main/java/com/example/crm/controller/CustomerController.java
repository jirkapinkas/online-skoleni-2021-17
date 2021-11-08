package com.example.crm.controller;

import com.example.api.pojo.Customer;
import com.example.api.rest.CustomerRestApi;
import com.example.crm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController implements CustomerRestApi {

    private final CustomerService customerService;

    @Override
    public Optional<Customer> findCustomer(int id) {
        log.info("Called CustomerController::customer, customerId = {}", id);
        return customerService.findOne(id);
    }

}
