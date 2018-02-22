package com.istio.mesh.example.istiolocation;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocationController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private PhaseService phaseService;

    @RequestMapping("forecast/{city}")
    public String index(@PathVariable("city") String city, Map<String, Object> model) {
//        model.put("weather", weatherService.weather(city));
        model.put("lunarPhase", phaseService.lunarPhase(city));
        return "forecast";
    }

    @RequestMapping("weather/{city}")
    public String weather(@PathVariable("city") String city, Map<String, Object> model) {
        model.put("weather", weatherService.weather(city));
        return "weather";
    }

    @RequestMapping("phase/{city}")
    public String phase(@PathVariable("city") String city, Map<String, Object> model) {
        model.put("lunarPhase", phaseService.lunarPhase(city));
        return "phase";
    }
}
