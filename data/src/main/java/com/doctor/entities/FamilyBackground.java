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
@Table(name = "family_background")
@EntityListeners(AuditingEntityListener.class)
public class FamilyBackground {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long antecedentId;

  @ManyToOne
  @JoinColumn(name = "patientId")
  private Patient patient;
  private String type;
  private String relation;
  private String description;
  private String consultationDetails;
  @CreatedDate
  private LocalDateTime creationDate;
  @LastModifiedDate
  private LocalDateTime modificationDate;
}
