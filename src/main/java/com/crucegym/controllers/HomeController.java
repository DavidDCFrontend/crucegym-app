package com.crucegym.controllers;

import com.crucegym.entities.Exercise;
import com.crucegym.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping("/home")
    public String showHome(Model model) {
        // Obtener todos los ejercicios desde la base de datos para mostrar en la 'HomePage'
        List<Exercise> exercises = exerciseRepository.findAll();
        model.addAttribute("exercises", exercises);

        return "home";
    }
}
