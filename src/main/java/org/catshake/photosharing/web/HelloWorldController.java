package org.catshake.photosharing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("/hello-world")
public class HelloWorldController {
    @RequestMapping("/")
    public String helloWorld(Model model, @RequestParam("name") Optional<String> name) {
        List<String> names = new ArrayList<>();
        names.add("Алексей");
        names.add("Саша");
        names.add("Людмила");
        names.add("Азамат");

        int randomNumber = ThreadLocalRandom.current().nextInt(names.size());

        String randomName = names.get(randomNumber);
        model.addAttribute("name", name.orElse(randomName));
        return "helloworld";
    }

    @RequestMapping("/home")
    public String accountWindow(Model model){
        return "home";
    }
}
