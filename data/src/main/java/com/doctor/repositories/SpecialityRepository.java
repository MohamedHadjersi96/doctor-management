/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.repositories;

import com.doctor.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

  Optional<Speciality> findByName(String name);
}
