package com.example.microcrm.service;

import com.example.microcrm.controller.CustomerController;
import com.example.microcrm.pojo.Customer;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
public class CustomerService2 {

    private static final Logger log = LoggerFactory.getLogger(CustomerService2.class);

    private final CustomerService customerService;

    public CustomerService2(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @CircuitBreaker(name = "backendA")
    @Bulkhead(name = "backendA", type = Bulkhead.Type.THREADPOOL)
    @TimeLimiter(name = "backendA")
    @Retry(name = "backendA", fallbackMethod = "fallback")
    @GetMapping("/customers/{id}")
    public CompletableFuture<Optional<Customer>> customer(@PathVariable int id) {
        Optional<Customer> customer = customerService.findOne(id);
        return CompletableFuture.completedFuture(customer);
    }

    public CompletableFuture<Optional<Customer>> fallback(int id, Throwable e) {
        return CompletableFuture.completedFuture(Optional.of(new Customer(0, null, null)));
    }

}
