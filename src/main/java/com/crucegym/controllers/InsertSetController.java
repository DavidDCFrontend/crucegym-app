package com.crucegym.controllers;

import com.crucegym.dtos.SetRegistrationDTO;
import com.crucegym.entities.Set;
import com.crucegym.security.CustomUserDetails;
import com.crucegym.services.RecordService;
import com.crucegym.services.RepetitionMaximumService;
import com.crucegym.services.SetService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InsertSetController {

    @Autowired
    SetService setService = new SetService();

    @Autowired
    RecordService recordService = new RecordService();

    @Autowired
    private RepetitionMaximumService repetitionMaximumService;

    @PostMapping("/insertSet")
    public String inserSet(@ModelAttribute SetRegistrationDTO registrationDTO,
                           @RequestParam("action") String action,
                           @RequestParam("exerciseName") String exerciseName,
                           Authentication authentication,
                           HttpSession session,
                           RedirectAttributes redirectAttributes,
                           Model model) {

        try {
            // Obtenemos usuario autenticado, accedemos a su id y asociamos al DTO               !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!(Obtenido en ExerciseController)
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Integer userId = userDetails.getId();
            registrationDTO.setIdUser(userId);
            registrationDTO.setDate(LocalDate.now());

            // Obtenemos el orden de la última serie o inicializamos
            Integer lastOrder = (Integer) session.getAttribute("lastOrder");
            if(lastOrder == null) {
                lastOrder = 0;
            }

            // Incrementamos el orden de la serie y añadimos al DTO
            lastOrder++;
            registrationDTO.setOrder(lastOrder.byteValue());

            // Guardamos el nuevo orden en la sesión
            session.setAttribute("lastOrder", lastOrder);

            // Llamada al servicio para registrar la serie y almacenar su id
            Set savedSet = setService.registerSet(registrationDTO, action);
            System.out.println("Tu serie ha sido guardada correctamente");

            Object sessionValue = session.getAttribute("trainingSeriesIds");
            System.out.println("Session Value: " + sessionValue);

            // Si el usuario eligió registrar y agregar al entrenamiento
            if ("registerAndAdd".equals(action)) {
                // Recuperar la lista existente de series o crear una nueva si no existe
                List<Long> trainingSeriesIds = (List<Long>) session.getAttribute("trainingSeriesIds");
                if (trainingSeriesIds == null) {
                    trainingSeriesIds = new ArrayList<>(); // Si no existe, crear nueva lista
                }

                // Agregar el nuevo id de la serie a la lista
                trainingSeriesIds.add(savedSet.getId());

                // Almacenar la lista actualizada en la sesión
                session.setAttribute("trainingSeriesIds", trainingSeriesIds);
            }

            // Comprobar records y registrar nueva serie si es un nuevo record
            recordService.updateFiveRepsRecord(registrationDTO, savedSet);
            recordService.updateVolumeLoadRecord(registrationDTO, savedSet);
            repetitionMaximumService.updateOneRepetitionMaximum(registrationDTO, savedSet);

            // Pasar los parámetros necesarios para la redirección
            redirectAttributes.addAttribute("idExercise", registrationDTO.getIdExercise());
            redirectAttributes.addAttribute("exerciseName", exerciseName);

            return "redirect:/exercise";
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "redirect:/index";
        }
    }
}
