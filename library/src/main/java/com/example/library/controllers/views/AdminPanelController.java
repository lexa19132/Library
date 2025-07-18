package com.example.library.controllers.views;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminPanelController {

    @GetMapping("/console")
    public String getAdminPanel() {
        return "/console";
    }
}
