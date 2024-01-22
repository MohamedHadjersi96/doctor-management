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
@Table(name = "documents")
@EntityListeners(AuditingEntityListener.class)
public class Document {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long documentId;

  @ManyToOne
  @JoinColumn(name = "consultationId")
  private Consultation consultation;
  private String type;
  private String fileContent;
  @CreatedDate
  private LocalDateTime creationDate;
  @LastModifiedDate
  private LocalDateTime modificationDate;

}
