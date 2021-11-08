package com.example.micromailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MicroMailingApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebClient webClientCrm() {
        return WebClient.create("http://crm:8080");
    }

    @Bean
    public WebClient webClientProducts() {
        return WebClient.create("http://products:8080");
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroMailingApplication.class, args);
    }

}
