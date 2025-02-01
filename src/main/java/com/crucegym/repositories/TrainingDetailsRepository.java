package com.crucegym.repositories;

import com.crucegym.entities.TrainingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingDetailsRepository extends JpaRepository<TrainingDetails, Long> {
}
