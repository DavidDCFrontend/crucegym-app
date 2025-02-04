package com.crucegym.services;

import com.crucegym.dtos.SetRegistrationDTO;
import com.crucegym.entities.Record;
import com.crucegym.entities.RecordType;
import com.crucegym.entities.Set;
import com.crucegym.repositories.RecordRepository;
import com.crucegym.repositories.RepetitionMaximumRepository;
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

    @Autowired
    private RepetitionMaximumRepository repetitionMaximumRepository;

    public void updateFiveRepsRecord(SetRegistrationDTO registrationDTO, Set savedSet) {
        // Almacenar peso y repeticiones de la nueva serie
        Short newWeight = registrationDTO.getWeight();
        Short newReps = registrationDTO.getReps();
        LocalDate newDate = registrationDTO.getDate();

        // Obtener el record actual de 5 repeticiones para el ejercicio y usuario
        Optional<Set> fiveRepsRecord = recordRepository.findRecordBy5RepsInExercise(
                RecordType.ABSOLUTE,
                registrationDTO.getIdExercise(),
                registrationDTO.getIdUser()
        );

        // Variables para almacenar el peso y la fecha del record actual
        Short weightAbRec = 0;
        LocalDate dateAbRec = null;

        // Si existe, obtener datos de record actual
        if (fiveRepsRecord.isPresent()) {
            Set dataAbsoluteRecord = fiveRepsRecord.get();
            weightAbRec = dataAbsoluteRecord.getWeight();
            dateAbRec = dataAbsoluteRecord.getSetDate();
        }

        // Si no existe un puntaje previo o el nuevo puntaje es mejor
        if (fiveRepsRecord == null || (newWeight > weightAbRec && newReps >= 5)) {

            // Si hay record, actualizar
            if (fiveRepsRecord.isPresent()) {
                Set currentSet = fiveRepsRecord.get();

                // Buscar dentro del Set el Record con tipo VL
                Optional<Record> vlRecordOpt = currentSet.getRecord().stream()
                        .filter(record -> record.getType() == RecordType.ABSOLUTE)
                        .findFirst();

                if (vlRecordOpt.isPresent()) {
                    Record abRecordToUpdate = vlRecordOpt.get();

                    // Actualizar el record
                    abRecordToUpdate.setIdSet(savedSet);
                    abRecordToUpdate.setType(RecordType.ABSOLUTE);

                    // Guardar record actualizado
                    recordRepository.save(abRecordToUpdate);
                }
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










    public void updateVolumeLoadRecord (SetRegistrationDTO registrationDTO, Set savedSet){

        // Almacenar peso y repeticiones de la nueva serie
        Short newWeight = registrationDTO.getWeight();
        Short newReps = registrationDTO.getReps();
        LocalDate newDate = registrationDTO.getDate();

        // Obtener el record actual de volumen de carga para el ejercicio y usuario
        Optional<Set> volumeLoadRecord = recordRepository.findRecordByVolumeLoad(
                RecordType.VL,
                registrationDTO.getIdExercise(),
                registrationDTO.getIdUser()
        );

        // Variables para almacenar el peso, las repeticiones y la fecha del record actual
        Short weightVLRec = 0;
        Short repsVLRec = 0;
        LocalDate dateVLRec = null;

        // Si existe, obtener datos de record actual
        if (volumeLoadRecord.isPresent()) {
            Set currentVLRecord = volumeLoadRecord.get();
            weightVLRec = currentVLRecord.getWeight();
            repsVLRec = currentVLRecord.getReps();
            dateVLRec = currentVLRecord.getSetDate();
        }

        // Si no existe un puntaje previo o el nuevo puntaje es mejor
        if (volumeLoadRecord == null || (newWeight * newReps > weightVLRec * repsVLRec)) {

            // Si hay record, actualizar
            if (volumeLoadRecord.isPresent()) {
                Set currentSet = volumeLoadRecord.get();

                // Buscar dentro del Set el Record con tipo VL
                Optional<Record> vlRecordOpt = currentSet.getRecord().stream()
                        .filter(record -> record.getType() == RecordType.VL)
                        .findFirst();

                if (vlRecordOpt.isPresent()) {
                    Record vlRecordToUpdate = vlRecordOpt.get();

                    // Actualizar el record
                    vlRecordToUpdate.setIdSet(savedSet);
                    vlRecordToUpdate.setType(RecordType.VL);

                    // Guardar record actualizado
                    recordRepository.save(vlRecordToUpdate);
                }
            } else {
                // Si no existe record lo creamos
                Record newVLRecord = new Record();
                newVLRecord.setIdSet(savedSet);
                newVLRecord.setType(RecordType.VL);

                // Guardar nuevo record en la base de datos
                recordRepository.save(newVLRecord);
            }
        }
    }
}

