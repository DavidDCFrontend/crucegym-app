package com.crucegym.repositories;

import com.crucegym.entities.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {

    @Query(value = "SELECT s.id FROM Set s WHERE s.id_user :=userId ORDER BY s.set_date DESC LIMIT 1", nativeQuery = true)
    Optional<Set> findLastSetByUserId(@Param("userId") Integer userId);
}


