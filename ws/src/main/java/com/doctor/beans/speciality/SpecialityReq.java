/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.beans.speciality;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder
public record  SpecialityReq (Long specialityId,
                              @NotBlank(message = "speciality name is mandatory") String name,
                              @Size(max=500, message = "max size of speciality description is {max}") String description){

}

