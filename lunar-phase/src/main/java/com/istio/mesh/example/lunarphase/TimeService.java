package com.istio.mesh.example.lunarphase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TimeService {

    private static final String ENDPOINT = "http://time.jsontest.com/";
    private static final DateTimeFormatter FMT = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("hh:mm:ss a")
            .toFormatter(Locale.US);

    private final RestTemplate restTemplate;

    public TimeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public LocalTime jsonTime(HttpHeaders headers) {
        try {
            ResponseEntity<JsonNode> entity =
                    restTemplate.exchange(ENDPOINT, HttpMethod.GET, new HttpEntity<>("parameters", headers), JsonNode.class);
            return LocalTime.parse(entity.getBody().get("time").asText(), FMT);
        } catch (Exception e) {
            return null;
        }
    }
}
