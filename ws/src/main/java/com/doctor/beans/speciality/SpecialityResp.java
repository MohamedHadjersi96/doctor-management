/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.beans.speciality;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import java.time.LocalDateTime;


@Builder
public record  SpecialityResp (Long specialityId,
                               String name,
                               String description,
                               @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime creationDate,
                               @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime modificationDate){
}
