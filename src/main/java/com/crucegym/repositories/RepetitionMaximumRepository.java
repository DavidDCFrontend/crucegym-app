package com.crucegym.repositories;

import com.crucegym.entities.OneRepetitionMaximum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepetitionMaximumRepository extends JpaRepository<OneRepetitionMaximum, Short> {

    @Query("SELECT o FROM OneRepetitionMaximum o " +
            "WHERE o.exercise.id = :idExercise AND o.user.id = :idUser")
    Optional<OneRepetitionMaximum> findByUserAndIdExercise(@Param("idExercise") Short idExercise, @Param("idUser") Integer idUser);
}
