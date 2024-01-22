/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.repositories;

import com.doctor.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
