package com.crucegym.controllers;

import com.crucegym.dtos.SetRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHome(Model model) {

        SetRegistrationDTO registrationDTO = new SetRegistrationDTO();
        registrationDTO.setDate(LocalDate.now());

        model.addAttribute("setRegistrationDTO", registrationDTO);

        return "home";
    }
}
