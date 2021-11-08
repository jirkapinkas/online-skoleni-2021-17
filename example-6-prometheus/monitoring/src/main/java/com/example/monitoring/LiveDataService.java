package com.example.monitoring;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LiveDataService {

    public double getData() {
        return (double) new Random().nextInt(100);
    }

}
