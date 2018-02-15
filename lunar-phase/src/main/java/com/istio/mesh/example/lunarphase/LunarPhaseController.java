package com.istio.mesh.example.lunarphase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LunarPhaseController {

    @GetMapping("/phase/today/{city}")
    public LunarPhase lunarPhase(@PathVariable("city") String city) {
        return new LunarPhase(city);
    }
}
