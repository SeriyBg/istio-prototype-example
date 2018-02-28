package com.istio.mesh.example.istioweather;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class IconService {

    private final RestTemplate restTemplate;
    private final String endpoint;

    public IconService(RestTemplate restTemplate, String endpoint) {
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    public String icon(String state, HttpHeaders headers) {
        try {
            ResponseEntity<String> entity =
                    restTemplate.exchange(endpoint + "/icon/" + state, HttpMethod.GET, new HttpEntity<>("parameters", headers), String.class);
            return entity.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
