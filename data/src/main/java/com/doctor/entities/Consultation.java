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

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "consultations")
@EntityListeners(AuditingEntityListener.class)
public class Consultation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long consultationId;

  @ManyToOne
  @JoinColumn(name = "doctorId")
  private Doctor doctor;

  @ManyToOne
  @JoinColumn(name = "patientId")
  private Patient patient;
  private LocalDate consultationDate;
  private String consultationType;
  private String consultationDetails;
  private String diagnostic;
  @CreatedDate
  private LocalDateTime creationDate;
  @LastModifiedDate
  private LocalDateTime modificationDate;
}
