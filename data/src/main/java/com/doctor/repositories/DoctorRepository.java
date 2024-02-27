/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.repositories;

import com.doctor.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {


  Optional<Doctor> findByEmail(String email);
  Optional<Doctor> findByPhoneNumber(String phoneNumber);

  List<Optional<Doctor>> findBySpeciality_SpecialityId(Long specialityId);
}
