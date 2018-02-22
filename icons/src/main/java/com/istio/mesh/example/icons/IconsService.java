package com.istio.mesh.example.icons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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

    private final ResourceLoader resourceLoader;

    @Autowired
    public IconsService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String iconForState(String state) throws IOException {
        String icon = STATE_TO_ICON.get(state);
        if (icon == null) {
            icon = "Umbrella.svg";
        }
        InputStream resource = resourceLoader.getResource("classpath:icons/" + icon).getInputStream();
        byte[] encoded = IOUtils.toByteArray(resource);
        return new String(encoded);
    }
}
