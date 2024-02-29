/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.mapper;

import com.doctor.beans.patient.PatientReq;
import com.doctor.beans.patient.PatientResp;
import com.doctor.entities.Patient;
import com.doctor.exceptions.ResourceNotFoundException;
import com.doctor.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper implements Mapper <Patient, PatientReq, PatientResp> {

  @Autowired
  private PatientRepository patientRepository;
  @Override
  public Patient mapToEntity(PatientReq request, MapperPattern pattern) {

    if(MapperPattern.CREATE == pattern){
      return Patient.builder()
              .patientId(request.patientId())
              .firstName(request.firstName())
              .lastName(request.lastName())
              .birthDate(request.birthDate())
              .gender(request.gender())
              .phoneNumber(request.phoneNumber())
              .email(request.email())
              .address(request.address())
              .socialSecurityNumber(request.socialSecurityNumber())
              .build();
    } else {
      final var existingPatient = patientRepository.findById(request.patientId())
              .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", request.patientId(), "PATIENT_NOT_FOUND"));

      existingPatient.setPatientId(request.patientId());
      existingPatient.setFirstName(request.firstName());
      existingPatient.setLastName(request.lastName());
      existingPatient.setGender(request.gender());
      existingPatient.setBirthDate(request.birthDate());
      existingPatient.setPhoneNumber(request.phoneNumber());
      existingPatient.setEmail(request.email());
      existingPatient.setAddress(request.address());
      existingPatient.setSocialSecurityNumber(request.socialSecurityNumber());
      return existingPatient;
    }

  }

  @Override
  public PatientResp mapToResponse(Patient entity) {

    return PatientResp.builder()
            .patientId(entity.getPatientId())
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .gender(entity.getGender())
            .birthDate(entity.getBirthDate())
            .phoneNumber(entity.getPhoneNumber())
            .email(entity.getEmail())
            .address(entity.getAddress())
            .socialSecurityNumber(entity.getSocialSecurityNumber())
            .creationDate(entity.getCreationDate())
            .modificationDate(entity.getModificationDate())
            .build();
    }

}
