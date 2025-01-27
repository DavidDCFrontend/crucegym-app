package com.crucegym.controllers;

import com.crucegym.dtos.SetRegistrationDTO;
import com.crucegym.services.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InsertSetController {

    @Autowired
    SetService setService = new SetService();

    @PostMapping("/insertSet")
    public String inserSet(@ModelAttribute SetRegistrationDTO registrationDTO, Model model) {

        try {
            setService.registerSet(registrationDTO);
            return "redirect:/home";
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "home";
        }
    }
}
