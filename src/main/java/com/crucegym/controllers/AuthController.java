package com.crucegym.controllers;

import com.crucegym.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        // Lógica para guardar el usuario
        return "redirect:/login"; // Redirige a login después de registrar
    }
}
