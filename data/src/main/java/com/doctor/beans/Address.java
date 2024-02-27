/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.beans;

import jakarta.persistence.Embeddable;
import lombok.*;


@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

  private String fullAddress;
  private String additionalAddress;
  private String zipCode;
  private String wilaya;
}
