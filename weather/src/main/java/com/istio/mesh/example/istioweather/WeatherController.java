package com.istio.mesh.example.istioweather;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private static final String USER_AGENT = "user-agent";
    private static final String[] TRACE_HEADERS = new String[] {"x-request-id", "x-b3-traceid", "x-b3-spanid", "x-b3-parentspanid", "x-b3-sampled", "x-b3-flags", "x-ot-span-context"};

    private final IconService iconService;

    @Autowired
    public WeatherController(IconService iconService) {
        this.iconService = iconService;
    }

    @GetMapping("/weather/{city}")
    public Weather weather(@PathVariable("city") String city, HttpServletRequest request) {
        Weather weather = new Weather(city);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(USER_AGENT, request.getHeader(USER_AGENT));
        for (String traceHeader : TRACE_HEADERS) {
            String header = request.getHeader(traceHeader);
            if (StringUtils.isEmpty(header)) {
                continue;
            }
            httpHeaders.add(traceHeader, header);
        }
        weather.setIcon(iconService.icon(weather.getDescription(), httpHeaders));
        return weather;
    }
}
