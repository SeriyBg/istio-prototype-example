package com.istio.mesh.example.istioweather;

import lombok.Getter;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Weather {

    private static final List<String> FORECAST_DESCRIPTION =
            Arrays.asList("Sunny", "Mostly Sunny", "Partly Cloudy", "Mostly Cloudy", "Cloudy", "Showers", "Rain", "Snow");

    private static final Random RANDOM = new Random();

    public Weather(String city) {
        this.city = city;
        temperature = ThreadLocalRandom.current().nextInt(-40, 40);
        description = FORECAST_DESCRIPTION.get(RANDOM.nextInt(FORECAST_DESCRIPTION.size()));
        when = Instant.now();
    }

    private String id;

    private String city;

    private int temperature;

    private String description;

    private Instant when;
}
