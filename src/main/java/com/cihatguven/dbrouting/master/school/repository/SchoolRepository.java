package com.cihatguven.dbrouting.master.school.repository;

import com.cihatguven.dbrouting.master.school.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, String> {
    Boolean existsByCode(String code);

    Optional<School> findByCode(String school);
}
