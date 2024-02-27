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
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "specialties")
@EntityListeners(AuditingEntityListener.class)
public class Speciality {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //créer une séquence
  private Long specialityId;
  private String name;
  private String description;

  @OneToMany(mappedBy = "speciality")
  private List<Doctor> doctors;

  @CreatedDate
  private LocalDateTime creationDate;
  @LastModifiedDate
  private LocalDateTime modificationDate;


}
