package com.example.library.controllers.views;

import com.example.library.model.User;
import com.example.library.storages.UserStorage;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserStorage userStorage;

    public RegistrationController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getRegistrationPage(Model model) {
        model.addAttribute("User", new User());
        return "/registrationPage";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("User") User user) {
        userStorage.createAndRegisterUser(user);
        return "redirect:/api/book/all";
    }
}
