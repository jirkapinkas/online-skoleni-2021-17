package com.example.micromailing;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCaching
@SpringBootApplication
public class MicroMailingApplication {

    @Bean
    HazelcastInstance hazelcastInstance() {
        ClientConfig clientConfig = new ClientConfig();
        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
        networkConfig.addAddress("hc1:5701", "hc2:5701", "hc3:5701");
        return HazelcastClient.newHazelcastClient(clientConfig);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(MicroMailingApplication.class, args);
    }

}
