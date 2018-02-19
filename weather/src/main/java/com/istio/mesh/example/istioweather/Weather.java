package com.istio.mesh.example.istioweather;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Weather {

    private static final List<String> FORECAST_DESCRIPTION =
            Arrays.asList("Sunny", "Mostly Sunny", "Partly Cloudy", "Mostly Cloudy", "Cloudy", "Showers", "Rain", "Snow");

    private static final Random RANDOM = new Random();

    public Weather(String city) {
        id = UUID.randomUUID().toString();
        this.city = city;
        temperature = ThreadLocalRandom.current().nextInt(-40, 40);
        description = FORECAST_DESCRIPTION.get(RANDOM.nextInt(FORECAST_DESCRIPTION.size()));
        when = LocalDate.now();
    }

    private String id;

    private String city;

    private int temperature;

    private String description;

    @Setter
    private String icon;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using= LocalDateSerializer.class)
    @JsonDeserialize(using=LocalDateDeserializer.class)
    private LocalDate when;
}
