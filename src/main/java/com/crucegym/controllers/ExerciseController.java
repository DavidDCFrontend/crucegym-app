package com.crucegym.controllers;

import com.crucegym.dtos.SetRegistrationDTO;
import com.crucegym.repositories.ExerciseRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ExerciseController {

    @PostMapping("/exercise")
    public String showExercise(@RequestParam("idExercise") Short idExercise,
                               @RequestParam("exerciseName") String exerciseName,
                               HttpSession session,
                               Model model) {

        // Crear DTO con el nombre del ejercicio y la fecha
        SetRegistrationDTO registrationDTO = new SetRegistrationDTO();
        registrationDTO.setDate(LocalDate.now());
        registrationDTO.setIdExercise(idExercise);

        model.addAttribute("setRegistrationDTO", registrationDTO);
        model.addAttribute("exerciseName", exerciseName);

        return "exercise";
    }
}

/*
@GetMapping("/exercise")
    public String showExercise(@RequestParam("idExercise") Short idExercise,
                               @RequestParam("exerciseName") String exerciseName,
                               HttpSession session,
                               Model model) {

        // Obtener usuario de la sesión
        Integer userId = (Integer) session.getAttribute("userId");

        // Crear DTO con el id del usuario y del ejercicio y fecha
        SetRegistrationDTO registrationDTO = new SetRegistrationDTO();
        registrationDTO.setIdUser(userId);
        registrationDTO.setDate(LocalDate.now());
        registrationDTO.setIdExercise(idExercise);

        model.addAttribute("setRegistrationDTO", registrationDTO);
        model.addAttribute("exerciseName", exerciseName);

        return "exercise";
    }
 */
