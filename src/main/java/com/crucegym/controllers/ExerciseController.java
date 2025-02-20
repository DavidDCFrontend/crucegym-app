package com.crucegym.controllers;

import com.crucegym.dtos.SetRegistrationDTO;
import com.crucegym.entities.OneRepetitionMaximum;
import com.crucegym.entities.RecordType;
import com.crucegym.entities.Set;
import com.crucegym.repositories.RecordRepository;
import com.crucegym.repositories.RepetitionMaximumRepository;
import com.crucegym.security.CustomUserDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class ExerciseController {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RepetitionMaximumRepository repetitionMaximumRepository;

    @RequestMapping(value = "/exercise", method = {RequestMethod.POST, RequestMethod.GET})
    public String showExercise(@RequestParam("idExercise") Short idExercise,
                               @RequestParam("exerciseName") String exerciseName,
                               Authentication authentication,
                               HttpSession session,
                               Model model) {

        List<Integer>weights = getWeightsForExercise(idExercise);
        model.addAttribute("weights", weights);

        // Reiniciar el orden al cambiar de ejercicio si es un nuevo ejercicio
        Short lastExercise = (Short) session.getAttribute("lastExercise");

        if(idExercise != lastExercise || lastExercise == null) {
            session.setAttribute("lastOrder", 0);
        }

        // Crear DTO
        SetRegistrationDTO registrationDTO = new SetRegistrationDTO();

        // Obtenemos usuario autenticado, accedemos a su id y asociamos al DTO
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getId();

        // Añadir usuario y ejercicio al DTO
        registrationDTO.setIdUser(userId);
        registrationDTO.setIdExercise(idExercise);
        model.addAttribute("setRegistrationDTO", registrationDTO);
        model.addAttribute("exerciseName", exerciseName);

        // Pasar las mejores marcas al modelo
        Optional<Set> fiveRepsRecord = recordRepository.findRecordBy5RepsInExercise(RecordType.ABSOLUTE, idExercise, userId);
        Optional<Set> volumeLoadRecord = recordRepository.findRecordByVolumeLoad(RecordType.VL, idExercise, userId);
        Optional<OneRepetitionMaximum> oneRepetitionMaximum = repetitionMaximumRepository.findByUserAndIdExercise(idExercise, userId);

        if(fiveRepsRecord.isPresent()) {
            Short weightFiveRepsRec = fiveRepsRecord.get().getWeight();
            Short repsFiveRepsRec = fiveRepsRecord.get().getReps();
            model.addAttribute("weightFiveRepsRec", weightFiveRepsRec);
            model.addAttribute("repsFiveRepsRec", repsFiveRepsRec);
        }

        if(volumeLoadRecord.isPresent()) {
            Short weightVLRec = volumeLoadRecord.get().getWeight();
            Short repsVLRec = volumeLoadRecord.get().getReps();
            model.addAttribute("weightVLRec", weightVLRec);
            model.addAttribute("repsVLRec", repsVLRec);
        }

        if(oneRepetitionMaximum.isPresent()) {
            float rm = oneRepetitionMaximum.get().getOneRepetitionMaximum();
            model.addAttribute("rm", rm);
        }

        return "exercise";
    }

    private List<Integer> getWeightsForExercise(Short idExercise) {
        if(idExercise == 1) {
            return Arrays.asList(10, 20, 30, 40, 50, 60);
        } else if(idExercise == 2){
            return Arrays.asList(20, 25, 30, 35, 40, 45);
        } else if(idExercise == 3) {
            return Arrays.asList(30, 40, 50, 60, 70, 80);
        } else {
            return Arrays.asList(1, 2, 3, 4, 5);
        }
    }
}