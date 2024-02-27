/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.beans.doctor;

import com.doctor.beans.Address;
import com.doctor.enumerations.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;
@Builder
public record DoctorReq(Long doctorId,
                        @NotNull(message = "specialityId is mandatory") Long specialityId,
                        @NotEmpty(message = "last name is mandatory") String lastName,
                        @NotEmpty(message = "first name is mandatory") String firstName,
                        @NotEmpty(message = "phone number name is mandatory") String phoneNumber,

                        @JsonFormat(pattern = "dd/MM/yyyy")
                        LocalDate birthDate,
                        Address address,
                        @NotNull(message = "gender  is mandatory") Gender gender,
                        @NotEmpty(message = "email  name is mandatory") @Email(message = "Email should be valid")  String email,
                        @NotEmpty(message = "social security number is mandatory") String socialSecurityNumber) {

}
