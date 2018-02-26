package com.istio.mesh.example.istiolocation;

import java.time.LocalTime;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PhaseService {

    private final RestTemplate restTemplate;
    private final String endpoint;

    public PhaseService(RestTemplate restTemplate, String endpoint) {
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    public LunarPhase lunarPhase(String city, HttpHeaders headers) {
        final String url = endpoint + "/phase/today/" + city;
        try {
            ResponseEntity<LunarPhase> exchange =
                    restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("parameters", headers), LunarPhase.class);
            return exchange.getBody();
        } catch (Exception e) {
            return new LunarPhase("Error", null, LocalTime.MIN, LocalTime.MAX);
        }
    }
}
