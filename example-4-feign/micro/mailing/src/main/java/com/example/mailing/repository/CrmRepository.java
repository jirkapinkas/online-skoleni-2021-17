package com.example.mailing.repository;

import com.example.api.pojo.Customer;
import com.example.api.rest.CustomerRestApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "crm", url = "http://crm:8080")
public interface CrmRepository /* extends CustomerRestApi */ {

    @GetMapping("/customers/{id}")
    Optional<Customer> findCustomer(@PathVariable int id);

}
