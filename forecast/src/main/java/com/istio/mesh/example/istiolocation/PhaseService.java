package com.istio.mesh.example.istiolocation;

import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

public class PhaseService {

    private final RestTemplate restTemplate;
    private final String endpoint;

    public PhaseService(RestTemplate restTemplate, String endpoint) {
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    public LunarPhase lunarPhase(String city) {
        final String url = endpoint + "/phase/today/" + city;
        try {
            return restTemplate.getForObject(url, LunarPhase.class);
        } catch (Exception e) {
            return new LunarPhase("Error", LocalTime.MIN, LocalTime.MAX);
        }
    }
}
