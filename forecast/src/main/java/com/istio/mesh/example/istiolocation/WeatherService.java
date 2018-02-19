package com.istio.mesh.example.istiolocation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Slf4j
public class WeatherService {

    private final RestTemplate restTemplate;
    private final String endpoint;

    public WeatherService(RestTemplate restTemplate, String endpoint) {
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    Weather weather(String city) {
        final String url = endpoint + "/weather/" + city;
        log.info("Url: {}", url);
        try {
            return restTemplate.getForObject(url, Weather.class);
        } catch (Exception e) {
            return new Weather("Error", 0, "Error", null, LocalDate.MIN);
        }
    }
}
