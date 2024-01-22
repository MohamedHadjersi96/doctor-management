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
@Table(name = "background_types")
@EntityListeners(AuditingEntityListener.class)
public class BackgroundType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long typeId;
  private String wording;
  private String description;
  private String category;
  @CreatedDate
  private LocalDateTime creationDate;
  @LastModifiedDate
  private LocalDateTime modificationDate;

}
