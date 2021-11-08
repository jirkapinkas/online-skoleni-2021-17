package com.example.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MonitoringApplication {


    @Bean
    public LiveDataMeterBinder temperatureMeterBinder(LiveDataService liveDataService) {
        return new LiveDataMeterBinder(liveDataService);
    }

    public static void main(String[] args) {
        SpringApplication.run(MonitoringApplication.class, args);
    }

}
