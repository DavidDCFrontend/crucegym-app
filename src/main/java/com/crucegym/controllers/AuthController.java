package com.crucegym.controllers;

import com.crucegym.dtos.UserRegistrationDTO;
import com.crucegym.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthController {

    @Autowired
    UserService userService = new UserService();

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("registrationDTO", new UserRegistrationDTO());
        return "login";
    }

  /*  @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registrationDTO", new UserRegistrationDTO());
        return "register";
    } */

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegistrationDTO registrationDTO, Model model) {

        try {
            userService.registerUser(registrationDTO);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
