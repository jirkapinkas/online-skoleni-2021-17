package com.example.crm.service;

import com.example.crm.entity.Customer;
import com.example.crm.entity.OutboxEvent;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.repository.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final OutboxRepository outboxRepository;

    private final ObjectMapper objectMapper;

    public Optional<Customer> findOne(int id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public Customer save(Customer customer) {
        try {
            customerRepository.save(customer);
            OutboxEvent outboxEvent = new OutboxEvent();
            outboxEvent.setAggregatetype("Customer");
            outboxEvent.setAggregateid(customer.getId());
            outboxEvent.setType("CREATE");
            outboxEvent.setPayload(objectMapper.writeValueAsString(customer));
            outboxRepository.save(outboxEvent);
            return customer;
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Transactional
    public void delete(int id) {
        try {
            customerRepository.deleteById(id);
            OutboxEvent outboxEvent = new OutboxEvent();
            outboxEvent.setAggregatetype("Customer");
            outboxEvent.setAggregateid(id);
            outboxEvent.setType("DELETE");
            outboxEvent.setPayload(objectMapper.writeValueAsString(id));
            outboxRepository.save(outboxEvent);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
