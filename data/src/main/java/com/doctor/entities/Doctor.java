/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */
package com.doctor.entities;

import com.doctor.beans.Address;
import com.doctor.enumerations.Gender;
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
@Table(name = "doctors")
@EntityListeners(AuditingEntityListener.class)
public class Doctor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long doctorId;

  @ManyToOne
  @JoinColumn(name = "specialityId")
  private Speciality speciality;

  private String lastName;
  private String firstName;
  private String phoneNumber;
  private LocalDate birthDate;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String email;
  @Embedded
  private Address address;
  private String socialSecurityNumber;
  @CreatedDate
  private LocalDateTime creationDate;
  @LastModifiedDate
  private LocalDateTime modificationDate;

}
