package com.crucegym.controllers;

import com.crucegym.dtos.RequestedTrainingDTO;
import com.crucegym.security.CustomUserDetails;
import com.crucegym.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TrainingHistoryController {

    @Autowired
    private TrainingService trainingService;

    @GetMapping("/training-history-form")
    public String showTrainingHistory() {

        return "training-history-form";
    }

    @PostMapping("/get-training")
    public String showTrainingHistory(@Param("date") LocalDate date,
                                      Authentication authentication,
                                      Model model) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getId();

        List<RequestedTrainingDTO> requestedTraining = trainingService.getRequestedTraining(userId, date);

        // Agrupar los entrenamientos por nombre de ejercicio
        Map<String, List<RequestedTrainingDTO>> groupedByExercise = requestedTraining.stream()
                .collect(Collectors.groupingBy(RequestedTrainingDTO::getExerciseName));

        model.addAttribute("groupedTraining", groupedByExercise);
        model.addAttribute("trainingDate", requestedTraining.get(0).getDate());

        return "training-search-result";
    }
}
