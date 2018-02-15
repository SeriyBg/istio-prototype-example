package com.istio.mesh.example.istiolocation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LunarPhase {

    private String phase;

    @JsonSerialize(using= LocalTimeSerializer.class)
    @JsonDeserialize(using=LocalTimeDeserializer.class)
    private LocalTime sunset;

    @JsonSerialize(using= LocalTimeSerializer.class)
    @JsonDeserialize(using=LocalTimeDeserializer.class)
    private LocalTime sunrise;
}