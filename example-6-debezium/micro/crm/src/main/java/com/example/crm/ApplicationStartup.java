package com.example.crm;

import com.example.crm.entity.Customer;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        {
            Customer customer = new Customer();
            customer.setName("Michael");
            customer.setEmail("michael@acme.com");
            customerService.save(customer);
        }
        {
            Customer customer = new Customer();
            customer.setName("Benny");
            customer.setEmail("benny@acme.com");
            customerService.save(customer);
        }
        {
            Customer customer = new Customer();
            customer.setName("Susan");
            customer.setEmail("susan@acme.com");
            customerService.save(customer);
        }
    }
}