package com.example.micromailing.service;

import com.example.micromailing.controller.MailingController;
import com.example.micromailing.pojo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CrmService {

    private static final Logger log = LoggerFactory.getLogger(CrmService.class);

    private final RestTemplate restTemplate;

    public CrmService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(cacheNames = "customers", key = "#customerId", sync = true)
    public Customer findCustomer(int customerId) {
        log.info("Called CrmService::findCustomer, customerId = {}", customerId);
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return restTemplate.getForObject("http://crm:8080/customers/" + customerId, Customer.class);
    }
}
