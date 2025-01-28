package com.crucegym.controllers;

import com.crucegym.dtos.SetRegistrationDTO;
import com.crucegym.security.CustomUserDetails;
import com.crucegym.services.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InsertSetController {

    @Autowired
    SetService setService = new SetService();

    @PostMapping("/insertSet")
    public String inserSet(@ModelAttribute SetRegistrationDTO registrationDTO, Authentication authentication, Model model) {

        try {
            // Obtenemos usuario autenticado, accedemos a su id y asociamos al DTO
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Integer userId = userDetails.getId();
            registrationDTO.setIdUser(userId);

            // Llamada al servicio para registrar la serie
            setService.registerSet(registrationDTO);
            System.out.println("Tu serie ha sido guardada correctamente");
            return "redirect:/home";
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "home";
        }
    }
}
