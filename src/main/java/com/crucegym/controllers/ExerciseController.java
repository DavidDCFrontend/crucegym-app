package com.crucegym.controllers;

import com.crucegym.dtos.SetRegistrationDTO;
import com.crucegym.entities.Set;
import com.crucegym.repositories.ExerciseRepository;
import com.crucegym.repositories.RecordRepository;
import com.crucegym.security.CustomUserDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class ExerciseController {

    @Autowired
    private RecordRepository recordRepository;

    @PostMapping("/exercise")
    public String showExercise(@RequestParam("idExercise") Short idExercise,
                               @RequestParam("exerciseName") String exerciseName,
                               Authentication authentication,
                               HttpSession session,
                               Model model) {

        // Crear DTO
        SetRegistrationDTO registrationDTO = new SetRegistrationDTO();

        // Obtenemos usuario autenticado, accedemos a su id y asociamos al DTO
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getId();

        // Añadir usuario, fecha y ejercicio al DTO
        registrationDTO.setIdUser(userId);
        registrationDTO.setDate(LocalDate.now());
        registrationDTO.setIdExercise(idExercise);
        model.addAttribute("setRegistrationDTO", registrationDTO);
        model.addAttribute("exerciseName", exerciseName);

        // Pasar las mejores marcas al modelo
        Optional<Set> fiveRepsRecord = recordRepository.findRecordBy5RepsInExercise(idExercise, userId);

        if(fiveRepsRecord.isPresent()) {
            Short weightFiveRepsRec = fiveRepsRecord.get().getWeight();
            Short repsFiveRepsRec = fiveRepsRecord.get().getReps();
            model.addAttribute("weightFiveRepsRec", weightFiveRepsRec);
            model.addAttribute("repsFiveRepsRec", repsFiveRepsRec);
        }

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
