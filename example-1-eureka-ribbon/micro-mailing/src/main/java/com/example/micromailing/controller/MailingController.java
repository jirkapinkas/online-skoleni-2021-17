package com.example.micromailing.controller;

import com.example.micromailing.pojo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MailingController {

    private static final Logger log = LoggerFactory.getLogger(MailingController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/constructEmail/{customerId}")
    public String constructEmail(@PathVariable int customerId) {
        log.info("Called MailingController::constructEmail, customerId = {}", customerId);
        Customer customer = restTemplate.getForObject("http://crm/customers/" + customerId, Customer.class);
        return "TODO send email to: " + customer.getEmail() + " to say hello to: " + customer.getName();
    }

}
