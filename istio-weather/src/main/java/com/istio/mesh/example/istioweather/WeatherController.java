package com.istio.mesh.example.istioweather;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @RequestMapping("weather/{city}")
    private Weather getWeather(@PathVariable("city") String city) {
        final Weather weather = new Weather(city);
        return weather;
    }
}
