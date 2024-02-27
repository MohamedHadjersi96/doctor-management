/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.mapper;

import com.doctor.beans.doctor.DoctorReq;
import com.doctor.beans.doctor.DoctorResp;
import com.doctor.beans.speciality.SpecialityReq;
import com.doctor.beans.speciality.SpecialityResp;
import com.doctor.entities.Doctor;
import com.doctor.entities.Speciality;
import com.doctor.exceptions.ResourceNotFoundException;
import com.doctor.repositories.DoctorRepository;
import com.doctor.repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper implements Mapper <Doctor, DoctorReq, DoctorResp> {

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private SpecialityRepository specialityRepository;

  @Autowired
  private Mapper<Speciality, SpecialityReq, SpecialityResp> mapper;

  @Override
  public Doctor mapToEntity(DoctorReq request, MapperPattern pattern) {
    final var existingSpeciality = specialityRepository.findById(request.specialityId())
            .orElseThrow(() ->new ResourceNotFoundException("Speciality", "id", request.specialityId(), "SPECIALITY_NOT_FOUND"));

    if(MapperPattern.CREATE == pattern){
      return Doctor.builder()
              .doctorId(request.doctorId())
              .speciality(existingSpeciality)
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
      final var existingDoctor = doctorRepository.findById(request.doctorId())
              .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", request.doctorId(), "DOCTOR_NOT_FOUND"));

      existingDoctor.setDoctorId(request.doctorId());
      existingDoctor.setSpeciality(existingSpeciality);
      existingDoctor.setFirstName(request.firstName());
      existingDoctor.setLastName(request.lastName());
      existingDoctor.setGender(request.gender());
      existingDoctor.setBirthDate(request.birthDate());
      existingDoctor.setPhoneNumber(request.phoneNumber());
      existingDoctor.setEmail(request.email());
      existingDoctor.setAddress(request.address());
      existingDoctor.setSocialSecurityNumber(request.socialSecurityNumber());
      return existingDoctor;
    }

  }

  @Override
  public DoctorResp mapToResponse(Doctor entity) {
    final Speciality speciality = entity.getSpeciality();

    if (speciality == null) {
      throw new ResourceNotFoundException("Speciality", "id", null, "SPECIALITY_NOT_FOUND");
    }
    final var existingSpeciality = specialityRepository.findById(speciality.getSpecialityId())
            .orElseThrow(() ->new ResourceNotFoundException("Speciality", "id", speciality.getSpecialityId(), "SPECIALITY_NOT_FOUND"));

    final var specialityResp = mapper.mapToResponse(existingSpeciality);

    return DoctorResp.builder()
            .doctorId(entity.getDoctorId())
            .speciality(specialityResp)
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
