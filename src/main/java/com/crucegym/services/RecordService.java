package com.crucegym.services;

import com.crucegym.dtos.SetRegistrationDTO;
import com.crucegym.entities.Record;
import com.crucegym.entities.RecordType;
import com.crucegym.entities.Set;
import com.crucegym.repositories.RecordRepository;
import com.crucegym.repositories.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private SetRepository setRepository;

    public void updateFiveRepsRecord(SetRegistrationDTO registrationDTO, Set savedSet) {
        // Almacenar peso y repeticiones de la nueva serie
        Short newWeight = registrationDTO.getWeight();
        Short newReps = registrationDTO.getReps();
        LocalDate newDate = registrationDTO.getDate();

        // Obtener el record actual de 5 repeticiones para el ejercicio y usuario
        Optional<Set> fiveRepsRecord = recordRepository.findRecordBy5RepsInExercise(
                registrationDTO.getIdExercise(),
                registrationDTO.getIdUser()
        );

        // Variables para almacenar el peso y la fecha del record actual
        Short weightAbRec = 0;
        LocalDate dateAbRec = null;

        // Si existe, obtener datos de record actual
        if(fiveRepsRecord.isPresent()) {
            Set dataAbsoluteRecord = fiveRepsRecord.get();
            weightAbRec =  dataAbsoluteRecord.getWeight();
            dateAbRec = dataAbsoluteRecord.getSetDate();
        }

        // Si no existe un puntaje previo o el nuevo puntaje es mejor
        if(fiveRepsRecord == null || (newWeight > weightAbRec && newReps >= 5)) {

            // Si hay record, actualizar
            if(fiveRepsRecord.isPresent()) {
                // Obtener registro record existente
                Record currentRecord = fiveRepsRecord.get().getRecord();

                // Actualizar el record
                currentRecord.setIdSet(savedSet);
                currentRecord.setType(RecordType.ABSOLUTE);

                // Guardar record actualizado
                recordRepository.save(currentRecord);
            } else {
                // Si no existe record lo creamos
                Record newFiveRepsRecord = new Record();
                newFiveRepsRecord.setIdSet(savedSet);
                newFiveRepsRecord.setType(RecordType.ABSOLUTE);

                // Guardar nuevo record en la base de datos
                recordRepository.save(newFiveRepsRecord);
            }
        }
    }
}
