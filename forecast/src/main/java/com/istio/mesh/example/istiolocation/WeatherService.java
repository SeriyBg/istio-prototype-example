package com.istio.mesh.example.istiolocation;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class WeatherService {

    private final RestTemplate restTemplate;
    private final String endpoint;

    public WeatherService(RestTemplate restTemplate, String endpoint) {
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    Weather weather(String city, HttpHeaders headers) {
        final String url = endpoint + "/weather/" + city;
        log.info("Url: {}", url);
        try {
            ResponseEntity<Weather> exchange =
                    restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("parameters", headers), Weather.class);
            return exchange.getBody();
        } catch (Exception e) {
            return new Weather("Error", 0, "Error", null, LocalDate.MIN);
        }
    }
}
