package org.catshake.photosharing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExplorePhotosController {
    @GetMapping("/explore")
    public String getExplore(Model model) {
        model.addAttribute("list", List.of(1, 2, 3));
        return "explore";
    }
}
