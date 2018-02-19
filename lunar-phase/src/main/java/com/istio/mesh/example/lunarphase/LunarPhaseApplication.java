package com.istio.mesh.example.lunarphase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LunarPhaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LunarPhaseApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    IconService iconService(RestTemplate restTemplate,
                            @Value("${backend.icon-service.url:localhost:8083}") String endpoint) {
        return new IconService(restTemplate, endpoint);
    }
}
