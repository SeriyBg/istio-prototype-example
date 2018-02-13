package com.istio.mesh.example.istiolocation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Weather {
    private String city;
    private int temperature;
    private String description;

    @JsonSerialize(using= LocalDateSerializer.class)
    @JsonDeserialize(using=LocalDateDeserializer.class)
    private LocalDate when;
}
