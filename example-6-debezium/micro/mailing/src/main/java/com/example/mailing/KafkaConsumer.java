package com.example.mailing;

import com.example.mailing.entity.Customer;
import com.example.mailing.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CustomerRepository customerRepository;

    private final ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(topics = "outbox.event.Customer", groupId = "customerInfo")
    public void consumeCustomerUpdates(String event) throws JsonProcessingException {
        log.info(String.format("#### -> Consumed order event -> %s", event));

        try(JsonReader jsonReader = Json.createReader(new StringReader(event))) {
            JsonObject json = jsonReader.readObject();
            JsonObject payload = json.containsKey("schema") ? json.getJsonObject("payload") : json;

            String eventType = payload.getString("eventType");
            switch (eventType) {
                case "CREATE":
                    log.info("TODO CREATE");
                    String eventPayload = payload.getString("payload");
                    Customer customer = objectMapper.readValue(eventPayload, Customer.class);
                    log.info("Customer to create = {}", customer);
                    customerRepository.save(customer);
                    break;
                case "DELETE":
                    log.info("TODO DELETE");
                    String stringId = payload.getString("payload");
                    log.info("ID to delete = {}", Integer.parseInt(stringId));
                    customerRepository.deleteById(Integer.parseInt(stringId));
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown type: " + eventType);
            }
        }
    }

}