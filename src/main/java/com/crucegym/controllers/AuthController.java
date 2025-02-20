package com.crucegym.controllers;

import com.crucegym.dtos.UserRegistrationDTO;
import com.crucegym.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AuthController {

    @Autowired
    UserService userService = new UserService();

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("registrationDTO", new UserRegistrationDTO());
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegistrationDTO registrationDTO,
                               RedirectAttributes redirectAttributes) {

        if (registrationDTO.getUsername().chars().anyMatch(Character::isWhitespace)) {
            redirectAttributes.addFlashAttribute("error", "*El nombre de usuario no puede contener espacios en blanco");
            return "redirect:/login";
        }
        else if (registrationDTO.getUsername().length() < 4 || registrationDTO.getUsername().length() > 15){
            redirectAttributes.addFlashAttribute("error", "*El nombre de usuario debe tener un mínimo de 4 caracteres y un máximo de 15");
            return "redirect:/login";
        }
        else if (registrationDTO.getEmail().chars().anyMatch(Character::isWhitespace)) {
            redirectAttributes.addFlashAttribute("error", "*El email no puede contener espacios en blanco");
            return "redirect:/login";
        }else if (registrationDTO.getPassword() != registrationDTO.getPasswordRepeated()) {
            redirectAttributes.addFlashAttribute("error", "*No has repetido la misma contraseña");
            return "redirect:/login";
        }

        try {
            userService.registerUser(registrationDTO);
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }
}
