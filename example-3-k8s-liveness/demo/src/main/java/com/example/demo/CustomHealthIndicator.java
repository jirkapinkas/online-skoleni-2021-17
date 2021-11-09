package com.example.demo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    private boolean running = true;

    @Override
    public Health health() {
        if (running) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
