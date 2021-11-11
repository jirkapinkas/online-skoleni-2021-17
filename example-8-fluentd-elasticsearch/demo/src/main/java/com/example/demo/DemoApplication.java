package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@EnableScheduling
@SpringBootApplication
public class DemoApplication {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Scheduled(fixedDelay = 1_000)
    public void run() {
        log.info("app is running {}", LocalDateTime.now());
    }

    @Scheduled(fixedDelay = 10_000)
    public void runWithException() {
        try {
            try {
                Integer.parseInt("xxx");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            log.error("Unable to parse int: xxx", e);
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
