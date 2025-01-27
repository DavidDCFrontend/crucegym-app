package com.crucegym.services;

import com.crucegym.dtos.SetRegistrationDTO;
import com.crucegym.entities.Exercise;
import com.crucegym.entities.Set;
import com.crucegym.entities.User;
import com.crucegym.repositories.ExerciseRepository;
import com.crucegym.repositories.SetRepository;
import com.crucegym.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetService {

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    public Set registerSet(SetRegistrationDTO registrationDTO) {

        User user = userRepository.findById(registrationDTO.getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Exercise exercise = exerciseRepository.findById(registrationDTO.getIdExercise())
                .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado"));

        Set set = new Set(
                user,
                exercise,
                registrationDTO.getDate(),
                registrationDTO.getReps(),
                registrationDTO.getOrder(),
                registrationDTO.getWeight());

        return setRepository.save(set);
    }
}
