package com.example.library.controllers.views;

import com.example.library.model.DTO.UserDTO;
import com.example.library.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getLoginPage(Model model) {
        model.addAttribute("User", new User());
        return "/loginPage";
    }
}
