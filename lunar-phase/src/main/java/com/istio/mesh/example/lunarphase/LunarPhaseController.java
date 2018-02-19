package com.istio.mesh.example.lunarphase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LunarPhaseController {

    @Autowired
    private IconService iconService;

    @GetMapping("/phase/today/{city}")
    public LunarPhase lunarPhase(@PathVariable("city") String city) {
        LunarPhase lunarPhase = new LunarPhase(city);
        lunarPhase.setIcon(iconService.icon(lunarPhase.getPhase()));
        return lunarPhase;
    }
}
