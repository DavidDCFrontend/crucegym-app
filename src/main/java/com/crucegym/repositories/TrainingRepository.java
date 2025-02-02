package com.crucegym.repositories;

import com.crucegym.dtos.RequestedTrainingDTO;
import com.crucegym.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
    @Query("SELECT new com.crucegym.dtos.RequestedTrainingDTO " +
            "(t.date, t.description, t.comments, td.exerciseOrder, e.name, s.setOrder, s.reps, s.weight) " +
            "FROM Training t " +
            "JOIN t.trainingDetails td " +
            "JOIN td.idSet s " +
            "JOIN s.idExercise e " +
            "WHERE t.idUser.id = :userId " +
            "AND t.date = :date " +
            "ORDER BY t.date, td.exerciseOrder, s.setOrder")
    List<RequestedTrainingDTO> findByUserAndDate(@Param("userId") Integer userId,
                                                 @Param("date") LocalDate date);

}
