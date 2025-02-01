package com.crucegym.controllers;

import com.crucegym.dtos.TrainingRequestDTO;
import com.crucegym.security.CustomUserDetails;
import com.crucegym.services.TrainingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RegisterTrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping("/registerTraining")
    public String registerTraining(HttpSession session,
                                   Authentication authentication,
                                   Model model) {

        try {
            // 1. Recuperar las series de la sesión
            List<Long> trainingSeriesIds = (List<Long>) session.getAttribute("trainingSeriesIds");

            if(trainingSeriesIds == null || trainingSeriesIds.isEmpty()) {
                model.addAttribute("errorTraining", "No hay series para registrar el entrenamiento");
                return "home";
            }

            // 2. Obtener id del usuario autenticado
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Integer userId = userDetails.getId();

            // 3. Crear un DTO con los datos del entrenamiento
            TrainingRequestDTO trainingRequestDTO = new TrainingRequestDTO();
            trainingRequestDTO.setDate(LocalDate.now());
            trainingRequestDTO.setUserId(userId);
            trainingRequestDTO.setSeriesIds(trainingSeriesIds);

            //4. Registrar el entrenamiento utilizando el servicio
            trainingService.registerTraining(trainingRequestDTO);

            //5. Eliminar atributo de sesión con las series realizadas
            session.removeAttribute("trainingSeriesIds");

            model.addAttribute("message", "Entrenamiento registrado correctamente");
            return "home";

        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar el entrenamiento: " + e.getMessage());
            return "home";
        }

    }
}
