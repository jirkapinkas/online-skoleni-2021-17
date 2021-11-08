package com.example.microcrm.service;

import com.example.microcrm.pojo.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private List<Customer> customers;

    @PostConstruct
    public void init() {
        customers = Arrays.asList(
                new Customer(1, "Michael", "michael@acme.com"),
                new Customer(2, "Benny", "benny@acme.com"),
                new Customer(3, "Susan", "susan@acme.com")
        );
    }

    public Optional<Customer> findOne(int id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findAny();
    }

}
