package com.istio.mesh.example.icons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class IconsService {

    private static final Map<String, String> STATE_TO_ICON = new ImmutableMap.Builder<String, String>()
            .put("Sunny", "Sun.svg")
            .put("Mostly Sunny", "Cloud-Sun.svg")
            .put("Partly Cloudy", "Cloud-Wind-Sun.svg")
            .put("Mostly Cloudy", "Cloud-Fog-Sun.svg")
            .put("Cloudy", "Cloud.svg")
            .put("Showers", "Cloud-Rain.svg")
            .put("Rain", "Cloud-Drizzle.svg")
            .put("Snow", "Cloud-Snow-Alt.svg")
            .put("New moon", "Moon-New.svg")
            .put("Waxing crescent", "Moon-Waxing-Crescent.svg")
            .put("First quarter", "Moon-First-Quarter.svg")
            .put("Waxing gibbous", "Moon-Waxing-Gibbous.svg")
            .put("Full moon", "Moon-Full.svg")
            .put("Waning gibbous", "Moon-Waning-Gibbous.svg")
            .put("Third quarter", "Moon-Last-Quarter.svg")
            .put("Waning crescent", "Moon-Waning-Crescent.svg")
            .build();

    public String iconForState(String state) throws IOException {
        String icon = STATE_TO_ICON.get(state);
        if (icon == null) {
            icon = "Umbrella.svg";
        }
        File resource = new ClassPathResource("icons/" + icon).getFile();
        byte[] encoded = Files.readAllBytes(Paths.get(resource.getPath()));
        return new String(encoded);
    }
}
