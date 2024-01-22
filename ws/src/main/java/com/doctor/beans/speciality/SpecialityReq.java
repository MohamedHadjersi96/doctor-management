/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.beans.speciality;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class SpecialityReq {

  private Long specialityId;
  @NotBlank(message = "speciality name is mandatory")
  private String name;

  @Size(max=500, message = "max size of speciality description is {max}")
  private String description;
}
