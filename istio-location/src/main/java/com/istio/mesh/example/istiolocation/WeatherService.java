package com.istio.mesh.example.istiolocation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class WeatherService {

    private final RestTemplate restTemplate;
    private final String endpoint;

    public WeatherService(RestTemplate restTemplate, String endpoint) {
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    String weather(String city) {
        final String url = endpoint + "/weather/" + city;
        log.info("Url: {}", url);
        return restTemplate.getForObject(url, String.class);
    }
}
