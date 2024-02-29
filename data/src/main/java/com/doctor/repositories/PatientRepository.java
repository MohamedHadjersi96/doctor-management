/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.repositories;

import com.doctor.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

  Optional<Patient> findByEmail(String email);
  Optional<Patient> findByPhoneNumber(String phoneNumber);
}
