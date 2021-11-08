package com.example.mailing.controller;

import com.example.mailing.entity.Customer;
import com.example.mailing.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailingController {

    private final CustomerRepository customerRepository;

    @GetMapping("/constructEmail/{customerId}")
    public String constructEmail(@PathVariable int customerId) {
        log.info("Called MailingController::constructEmail, customerId = {}", customerId);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return "TODO send email to: " + customer.getEmail() + " to say hello to: " + customer.getName();
        } else {
            return null;
        }
    }

}
