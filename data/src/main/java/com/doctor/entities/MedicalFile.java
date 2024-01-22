/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "medical_file")
@EntityListeners(AuditingEntityListener.class)
public class MedicalFile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long medicalFileId;

  @ManyToOne
  @JoinColumn(name = "patientId")
  private Patient patient;

  private String notes;
  private String prescribedTreatments;
  private String medicalNotes;
  @CreatedDate
  private LocalDateTime creationDate;
  @LastModifiedDate
  private LocalDateTime modificationDate;

}
