package com.crucegym.services;

import com.crucegym.dtos.RequestedTrainingDTO;
import com.crucegym.dtos.TrainingRequestDTO;
import com.crucegym.entities.*;
import com.crucegym.repositories.SetRepository;
import com.crucegym.repositories.TrainingDetailsRepository;
import com.crucegym.repositories.TrainingRepository;
import com.crucegym.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
public class TrainingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private TrainingDetailsRepository trainingDetailsRepository;

    @Transactional
    public void registerTraining(TrainingRequestDTO trainingRequestDTO) {

        // 1. Obtener el objeto User a partir del ID
        User user = userRepository.findById(trainingRequestDTO.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado con ID: " + trainingRequestDTO.getUserId()));

        // 2. Crear entrenamiento
        Training training = new Training();
        training.setDate(trainingRequestDTO.getDate());
        training.setIdUser(user);

        // 3. Guardar entrenamiento
        Training savedTraining = trainingRepository.save(training);

        // 4. Inicializar variables para el orden de los ejercicios
        Byte exerciseOrder = 1;
        Short previousExerciseId = null;

        // 5. Asociar las series al entrenamiento en la tabla 'training_details'
        for(Long setId : trainingRequestDTO.getSeriesIds()) {
            // Obtener el objeto Set a partir del ID
            Set set = setRepository.findById(setId)
                    .orElseThrow(()
                            -> new RuntimeException("Serie no encontrada con ID: " + setId));

            // Comparar el 'id_exercise' actual con el anterior
            Short currentExerciseId = set.getIdExercise().getId();
            if(previousExerciseId != null && !currentExerciseId.equals(previousExerciseId)) {
                exerciseOrder++;
            }

            // Crear el detalle del entrenamiento
            TrainingDetails trainingDetails = new TrainingDetails();
            trainingDetails.setIdTraining(savedTraining);
            trainingDetails.setIdSet(set);
            trainingDetails.setExerciseOrder(exerciseOrder);

            // Guardar el detalle en la BBDD
            trainingDetailsRepository.save(trainingDetails);

            // Actualizar el 'previousExerciseId' para la siguiente iteración
            previousExerciseId = currentExerciseId;
        }
    }

    public List<RequestedTrainingDTO> getRequestedTraining(@Param("userId") Integer userId,
                                                           @Param("date")LocalDate date) {

        return trainingRepository.findByUserAndDate(userId, date);
    }

}
