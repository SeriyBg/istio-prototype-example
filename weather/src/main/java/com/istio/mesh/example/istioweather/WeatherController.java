package com.istio.mesh.example.istioweather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private IconService iconService;

    @RequestMapping("weather/{city}")
    private Weather getWeather(@PathVariable("city") String city) {
        Weather weather = new Weather(city);
        weather.setIcon(iconService.icon(weather.getDescription()));
        return weather;
    }
}
