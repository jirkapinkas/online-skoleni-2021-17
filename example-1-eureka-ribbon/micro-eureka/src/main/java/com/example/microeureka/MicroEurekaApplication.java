package com.example.microeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroEurekaApplication.class, args);
    }

}
