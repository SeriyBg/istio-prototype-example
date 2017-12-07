package com.istio.mesh.example.istioweather;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Document
@Getter
public class Weather {

    private static final List<String> FORECAST_DESCRIPTION =
            Arrays.asList("Sunny", "Mostly Sunny", "Partly Cloudy", "Mostly Cloudy", "Cloudy", "Showers", "Rain", "Snow");

    private static final Random RANDOM = new Random();

    public Weather(String city) {
        this.city = city;
        temperature = ThreadLocalRandom.current().nextInt(-40, 40);
        description = FORECAST_DESCRIPTION.get(RANDOM.nextInt(FORECAST_DESCRIPTION.size()));
    }

    @Id
    private String id;

    private String city;

    private int temperature;

    private String description;
}
