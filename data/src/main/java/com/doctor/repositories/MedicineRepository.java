/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.repositories;

import com.doctor.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
