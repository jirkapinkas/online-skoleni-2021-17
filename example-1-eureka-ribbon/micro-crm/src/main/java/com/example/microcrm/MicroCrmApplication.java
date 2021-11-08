package com.example.microcrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroCrmApplication.class, args);
    }

}
