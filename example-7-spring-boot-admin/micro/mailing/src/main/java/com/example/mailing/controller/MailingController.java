package com.example.mailing.controller;

import com.example.api.pojo.Customer;
import com.example.mailing.repository.CrmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class MailingController {

    private static final Logger log = LoggerFactory.getLogger(MailingController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CrmRepository crmRepository;

    @GetMapping("/constructEmail/{customerId}")
    public String constructEmail(@PathVariable int customerId) {
        log.info("Called MailingController::constructEmail, customerId = {}", customerId);
        Optional<Customer> optionalCustomer = crmRepository.findCustomer(customerId);
//        Customer customer = restTemplate.getForObject("http://crm:8080/customers/" + customerId, Customer.class);
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return "TODO send email to: " + customer.getEmail() + " to say hello to: " + customer.getName();
        } else {
            return null;
        }
    }

}
