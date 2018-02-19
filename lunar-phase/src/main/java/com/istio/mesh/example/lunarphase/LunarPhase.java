package com.istio.mesh.example.lunarphase;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Getter
public class LunarPhase {

    public static final List<String> MOON_PHASES = Arrays.asList("New moon", "Waxing crescent", "First quarter", "Waxing gibbous", "Full moon", "Waning gibbous", "Third quarter", "Waning crescent");
    private static final Random RANDOM = new Random();

    private String city;

    private String phase;

    @JsonSerialize(using= LocalTimeSerializer.class)
    @JsonDeserialize(using=LocalTimeDeserializer.class)
    private LocalTime sunset;

    @JsonSerialize(using= LocalTimeSerializer.class)
    @JsonDeserialize(using=LocalTimeDeserializer.class)
    private LocalTime sunrise;

    @Setter
    private String icon;

    LunarPhase(String city) {
        this.city = city;
        phase = MOON_PHASES.get(RANDOM.nextInt(MOON_PHASES.size()));
        sunset = LocalTime.MIDNIGHT.minusHours(RANDOM.nextInt(5));
        sunrise = LocalTime.NOON.minusHours(RANDOM.nextInt(5));
    }
}
