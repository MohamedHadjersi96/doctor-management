/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.beans.doctor;

import com.doctor.beans.Address;
import com.doctor.beans.speciality.SpecialityResp;
import com.doctor.enumerations.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record DoctorResp( Long doctorId,
                          SpecialityResp speciality,
                          String lastName,
                          String firstName,
                          String phoneNumber,
                          @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate,
                          Address address,
                          Gender gender,
                          String email,
                          String socialSecurityNumber,
                          @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime creationDate,
                          @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime modificationDate) {
}
