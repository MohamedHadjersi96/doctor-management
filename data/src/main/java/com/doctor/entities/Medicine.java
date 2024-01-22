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
@Table(name = "medicines")
@EntityListeners(AuditingEntityListener.class)
public class Medicine {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long medicineId;
  private String name;
  private String dosage;
  private String doctorInstructions;
  private String shapes;
  @CreatedDate
  private LocalDateTime creationDate;
  @LastModifiedDate
  private LocalDateTime modificationDate;


}
