package com.example.microcrm.service;

import com.example.microcrm.pojo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomerService {

    private List<Customer> customers;

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private AtomicInteger counter = new AtomicInteger(0);

    @PostConstruct
    public void init() {
        customers = Arrays.asList(
                new Customer(1, "Michael", "michael@acme.com"),
                new Customer(2, "Benny", "benny@acme.com"),
                new Customer(3, "Susan", "susan@acme.com")
        );
    }

    public Optional<Customer> findOne(int id) {
        log.info("Called CustomerService::findOne");
        // simuluje, ze tato operace trva 10 vterin
//        try {
//            Thread.sleep(10_000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // simuluje, ze tato operace v 50% pripadu vyhodi chybu
        if(counter.incrementAndGet() % 2 == 0) {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException();
        }
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findAny();
    }

}
