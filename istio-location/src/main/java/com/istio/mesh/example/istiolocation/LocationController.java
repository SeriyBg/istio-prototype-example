package com.istio.mesh.example.istiolocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class LocationController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("location/{city}")
    public String location(@PathVariable("city") String city, Map<String, Object> model) {
        model.put("weather", weatherService.weather(city));
        return "location";
    }
}
