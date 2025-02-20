package com.crucegym.controllers;

import com.crucegym.entities.Exercise;
import com.crucegym.repositories.SetRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TrainingController {

    @Autowired
    private SetRepository setRepository;

    @GetMapping("/current-training")
    public String showCurrentTraining(HttpSession session,
                                      Model model) {

        // 1. Recuperar las series de la sesión
        List<Long> trainingSeriesIds = (List<Long>) session.getAttribute("trainingSeriesIds");

        // 2. Obtener detalles de las series realizadas en el entrenamiento en curso
        if (trainingSeriesIds != null && !trainingSeriesIds.isEmpty()) {

            List<Object[]> currentTraining = trainingSeriesIds != null && !trainingSeriesIds.isEmpty()
                    ? setRepository.findSetDetailsByIds(trainingSeriesIds)
                    : new ArrayList<>();

            // Mapa para agrupar series por ejercicio (LinkedHashMap mantiene el orden de inserción)
            Map<String, List<String>> groupedExercises = new LinkedHashMap<>();

            // Procesar los datos
            for (Object[] row : currentTraining) {
                String exerciseName = (String) row[2]; // Nombre del ejercicio
                Short reps = (Short) row[0]; // Número de repeticiones
                Short weight = (Short) row[1]; // Peso usado

                // Formatear la serie
                String setInfo = "Serie " + (groupedExercises.getOrDefault(exerciseName, new ArrayList<>()).size() + 1) +
                        " - " + reps + " repeticiones con " + weight + " kg";

                // Agregar al mapa
                groupedExercises.computeIfAbsent(exerciseName, k -> new ArrayList<>()).add(setInfo);
            }

            // Pasar los datos al modelo
            model.addAttribute("groupedExercises", groupedExercises);

            // Mostrar la lista formateada en consola o en una vista                      !!!!!!! SOLO PARA VER EN CONSOLA TEXTO FORMATEADO (BORRAR EN PRODUCCIÓN) !!!!!!!
            int exerciseCounter = 1;
            for (Map.Entry<String, List<String>> entry : groupedExercises.entrySet()) {
                System.out.println("Ejercicio " + exerciseCounter + " - " + entry.getKey());
                for (String set : entry.getValue()) {
                    System.out.println(set);
                }
                System.out.println(); // Línea en blanco entre ejercicios
                exerciseCounter++;
            }
        }


        return "fragments/training-session :: training-content";
    }
}

/*
@Controller
public class TrainingController {

    @GetMapping("/training")
    public String showCurrentTraining() {

        return "training-session";
    }
}
 */
