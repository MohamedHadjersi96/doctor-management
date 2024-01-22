/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.repositories;

import com.doctor.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
