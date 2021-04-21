package org.catshake.photosharing.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExplorePhotosController {
    @GetMapping("/explore")
    public String getExplore() {
        return "explore";
    }
}
