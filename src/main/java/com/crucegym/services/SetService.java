package com.crucegym.services;

import com.crucegym.dtos.SetRegistrationDTO;
import com.crucegym.entities.Set;
import com.crucegym.repositories.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetService {

    @Autowired
    private SetRepository setRepository;

    public Set registerSet(SetRegistrationDTO registrationDTO) {

        Set set = new Set(registrationDTO.getIdExercise(), registrationDTO.getIdUser(), registrationDTO.getDate(), registrationDTO.getOrder(), registrationDTO.getWeight());
        return setRepository.save(set);
    }
}
