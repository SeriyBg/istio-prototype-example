package com.istio.mesh.example.istiolocation;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocationController {

    private static final String USER_AGENT = "user-agent";
    private static final String[] TRACE_HEADERS = new String[] {"x-request-id", "x-b3-traceid", "x-b3-spanid", "x-b3-parentspanid", "x-b3-sampled", "x-b3-flags", "x-ot-span-context"};

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private PhaseService phaseService;

    @RequestMapping("forecast/{city}")
    public String index(@PathVariable("city") String city, Map<String, Object> model) {
        return "forecast";
    }

    @RequestMapping("weather/{city}")
    public String weather(@PathVariable("city") String city, HttpServletRequest request, Map<String, Object> model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(USER_AGENT, request.getHeader(USER_AGENT));
        for (String traceHeader : TRACE_HEADERS) {
            httpHeaders.add(traceHeader, request.getHeader(traceHeader));
        }
        model.put("weather", weatherService.weather(city, httpHeaders));
        return "weather";
    }

    @RequestMapping("phase/{city}")
    public String phase(@PathVariable("city") String city, HttpServletRequest request, Map<String, Object> model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(USER_AGENT, request.getHeader(USER_AGENT));
        for (String traceHeader : TRACE_HEADERS) {
            httpHeaders.add(traceHeader, request.getHeader(traceHeader));
        }
        model.put("lunarPhase", phaseService.lunarPhase(city, httpHeaders));
        return "phase";
    }
}
