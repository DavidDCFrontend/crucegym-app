package com.crucegym.repositories;

import com.crucegym.entities.Record;
import com.crucegym.entities.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

    @Query("SELECT s FROM Set s " +
           "JOIN Record r ON s.id = r.idSet.id " +
           "WHERE r.type = 'ABSOLUTE' AND s.idExercise.id = :idExercise AND s.idUser.id = :idUser")
    Optional<Set> findRecordBy5RepsInExercise(@Param("idExercise") Short idExercise, @Param("idUser") Integer idUser);
}
