package com.crucegym.services;

import com.crucegym.dtos.SetRegistrationDTO;
import com.crucegym.entities.Exercise;
import com.crucegym.entities.OneRepetitionMaximum;
import com.crucegym.entities.Set;
import com.crucegym.entities.User;
import com.crucegym.repositories.ExerciseRepository;
import com.crucegym.repositories.RepetitionMaximumRepository;
import com.crucegym.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RepetitionMaximumService {

    @Autowired
    private RepetitionMaximumRepository repetitionMaximumRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    public void updateOneRepetitionMaximum(SetRegistrationDTO registrationDTO, Set savedSet) {

        if (registrationDTO.getReps() > 15) {
            return;
        }

        // Almacenar peso y repeticiones de la nueva serie
        Short newWeight = registrationDTO.getWeight();
        Short newReps = registrationDTO.getReps();
        LocalDate newDate = registrationDTO.getDate();

        // Obtener el one-repetition maximum para el ejercicio y usuario
        Optional<OneRepetitionMaximum> oneRepetitionMaximumFound = repetitionMaximumRepository.findByUserAndIdExercise(
                registrationDTO.getIdExercise(),
                registrationDTO.getIdUser()

        );

        // Variables para almacenar el one-repetition maximum actual y su fecha
        float currentRM = 0;
        Short brzyckiCandidate = (short) (newWeight * (36 / (37 - newReps)));
        Short epleyCandidate = (short) (newWeight * (1 + (newReps / 30.0)));
        LocalDate dateRMRec = null;

        // Si existe, obtener datos de one-repetition maximum actual
        if (oneRepetitionMaximumFound.isPresent()) {
            OneRepetitionMaximum oneRepetitionMaximum = oneRepetitionMaximumFound.get();
            currentRM = oneRepetitionMaximum.getOneRepetitionMaximum();
            dateRMRec = oneRepetitionMaximum.getDate();
        }

        // Para menos de 10 repeticiones usamos la fórmula de Brzycki
        if(newReps < 10) {
            // Si no existe un puntaje previo o el nuevo puntaje es mejor
            if (oneRepetitionMaximumFound == null || (brzyckiCandidate > currentRM)){

                // Si hay record, actualizar
                if (oneRepetitionMaximumFound.isPresent()) {
                    // Obtener registro record existente
                    OneRepetitionMaximum repMaxToUpdate = oneRepetitionMaximumFound.get();
                    // Actualizar el record
                    repMaxToUpdate.setOneRepetitionMaximum(brzyckiCandidate);

                    // Guardar record actualizado
                    repetitionMaximumRepository.save(repMaxToUpdate);
                } else {
                    // Si no existe record lo creamos
                    User user = userRepository.findById(registrationDTO.getIdUser())
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                    Exercise exercise = exerciseRepository.findById(registrationDTO.getIdExercise())
                            .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado"));

                    OneRepetitionMaximum newRM = new OneRepetitionMaximum();
                    newRM.setUser(user);
                    newRM.setExercise(exercise);
                    newRM.setOneRepetitionMaximum(brzyckiCandidate);
                    newRM.setDate(newDate);

                    // Guardar nuevo record en la base de datos
                    repetitionMaximumRepository.save(newRM);
                }
            }
        }

        // Para más de 10 repeticiones y hasta 15 usamos la fórmula de Epley
        if(newReps >= 10 && newReps <=15) {
            // Si no existe un puntaje previo o el nuevo puntaje es mejor
            if (oneRepetitionMaximumFound == null || (epleyCandidate > currentRM)){

                // Si hay record, actualizar
                if (oneRepetitionMaximumFound.isPresent()) {
                    // Obtener registro record existente
                    OneRepetitionMaximum repMaxToUpdate = oneRepetitionMaximumFound.get();
                    // Actualizar el record
                    repMaxToUpdate.setOneRepetitionMaximum(epleyCandidate);

                    // Guardar record actualizado
                    repetitionMaximumRepository.save(repMaxToUpdate);
                } else {
                    // Si no existe record lo creamos
                    User user = userRepository.findById(registrationDTO.getIdUser())
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                    Exercise exercise = exerciseRepository.findById(registrationDTO.getIdExercise())
                            .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado"));

                    OneRepetitionMaximum newRM = new OneRepetitionMaximum();
                    newRM.setUser(user);
                    newRM.setExercise(exercise);
                    newRM.setOneRepetitionMaximum(brzyckiCandidate);
                    newRM.setDate(newDate);

                    // Guardar nuevo record en la base de datos
                    repetitionMaximumRepository.save(newRM);
                }
            }
        }
    }
}
