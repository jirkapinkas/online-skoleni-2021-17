package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @Autowired
    private CustomHealthIndicator customHealthIndicator;

    @Value("${app.message}")
    private String appMessage;

    @GetMapping("/")
    public Message message() {
        return new Message(appMessage);
    }

    @GetMapping("/simulateOutage")
    public void simulateOutage() {
        customHealthIndicator.setRunning(false);
    }

    @GetMapping("/simulateCrash")
    public void simulateCrash() {
        System.exit(1);
    }

}
