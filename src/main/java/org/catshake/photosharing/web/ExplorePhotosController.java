package org.catshake.photosharing.web;

import lombok.AllArgsConstructor;
import org.catshake.photosharing.domain.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ExplorePhotosController {
    private final PostRepository postRepository;

    @GetMapping("/explore")
    public String getExplore(Model model) {

        model.addAttribute("posts", postRepository.findAll());
        return "explore";
    }
}
