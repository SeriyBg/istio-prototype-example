package com.istio.mesh.example.istiolocation;

import org.springframework.web.client.RestTemplate;

public class PhaseService {

    private final RestTemplate restTemplate;
    private final String endpoint;

    public PhaseService(RestTemplate restTemplate, String endpoint) {
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    public LunarPhase lunarPhase(String city) {
        final String url = endpoint + "/phase/today/" + city;
        return restTemplate.getForObject(url, LunarPhase.class);
    }
}
