package org.catshake.photosharing.web.user;

import org.catshake.photosharing.domain.User;
import org.catshake.photosharing.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/sign-up")
    String getUserForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    String getUserFormPost(@Valid UserForm userForm, BindingResult bResult) {
        if (bResult.hasErrors()) {
            return "sign-up";
        } else {
            userRepository.saveUser(new User(null, userForm.getUsername(), userForm.getPassword()));
            return "redirect:/";
        }
    }
}
