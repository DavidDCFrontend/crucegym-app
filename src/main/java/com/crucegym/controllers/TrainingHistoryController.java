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
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TrainingHistoryController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping("/get-training")
    public String showTrainingHistory(@Param("date") LocalDate date,
                                      Authentication authentication,
                                      Model model) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getId();

        List<RequestedTrainingDTO> requestedTraining = trainingService.getRequestedTraining(userId, date);

        if(requestedTraining.isEmpty()) {
            // No hay entrenamientos para la fecha seleccionada
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = date.format(formatter);
            model.addAttribute("noTrainingFound", true);
            model.addAttribute("trainingDate", formattedDate);
        } else {
            // Agrupar los entrenamientos por nombre de ejercicio
            Map<String, List<RequestedTrainingDTO>> groupedByExercise = requestedTraining.stream()
                    .collect(Collectors.groupingBy(RequestedTrainingDTO::getExerciseName));

            model.addAttribute("groupedTraining", groupedByExercise);
            model.addAttribute("trainingDate", date);
        }

        return "fragments/history-training :: history-content";
    }
}
