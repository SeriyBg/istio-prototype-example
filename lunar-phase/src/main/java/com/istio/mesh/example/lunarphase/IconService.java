package com.istio.mesh.example.lunarphase;

import org.springframework.web.client.RestTemplate;

public class IconService {
    private final RestTemplate restTemplate;
    private final String endpoint;

    public IconService(RestTemplate restTemplate, String endpoint) {
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    public String icon(String state) {
        try {
            return restTemplate.getForObject(endpoint + "/icon/" + state, String.class);
        } catch (Exception e) {
            return null;
        }
    }
}
