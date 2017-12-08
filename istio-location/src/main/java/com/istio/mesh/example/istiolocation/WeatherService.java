package com.istio.mesh.example.istiolocation;

import org.springframework.web.client.RestTemplate;

public class WeatherService {

    private final RestTemplate restTemplate;
    private final String endpoint;

    public WeatherService(RestTemplate restTemplate, String endpoint) {
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    String weather(String city) {
        return restTemplate.getForObject(endpoint + "/weather/" + city, String.class);
    }
}
