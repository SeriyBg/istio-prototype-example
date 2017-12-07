package com.istio.mesh.example.istiolocation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocationController {

    @RequestMapping("location")
    public String location() {
        return "location";
    }
}
