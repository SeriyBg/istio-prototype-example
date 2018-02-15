package com.istio.mesh.example.icons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class IconsController {

    @Autowired
    private IconsService iconsService;

    @GetMapping("icon/{state}")
    public String svgIcon(@PathVariable("state") String state) throws IOException {
        return iconsService.iconForState(state);
    }
}
