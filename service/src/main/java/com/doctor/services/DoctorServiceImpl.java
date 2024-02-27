/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;

import com.doctor.beans.doctor.DoctorReq;
import com.doctor.beans.doctor.DoctorResp;
import com.doctor.entities.Doctor;
import com.doctor.exceptions.DoctorAlreadyExistException;
import com.doctor.exceptions.ResourceNotFoundException;
import com.doctor.mapper.Mapper;
import com.doctor.mapper.MapperPattern;
import com.doctor.repositories.DoctorRepository;
import com.doctor.repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private SpecialityRepository specialityRepository;

  @Autowired
  private Mapper<Doctor, DoctorReq, DoctorResp> mapper;
  @Override
  public DoctorResp create(DoctorReq doctorReq) {
    doctorRepository.findByEmail(doctorReq.email())
          .ifPresent(existingSpeciality -> {
             throw new DoctorAlreadyExistException("Doctor email " + doctorReq.email() + " Already Exists");
    });

    return mapper.mapToResponse(doctorRepository.save(mapper.mapToEntity(doctorReq, MapperPattern.CREATE)));
  }

  @Override
  public DoctorResp update(DoctorReq doctorReq) {
    this.validateDoctor(doctorReq.specialityId());
    return mapper.mapToResponse(doctorRepository.save(mapper.mapToEntity(doctorReq, MapperPattern.UPDATE)));
  }

  @Override
  public DoctorResp find(Long doctorId) {
    final Doctor doctor = this.validateDoctor(doctorId);
    return mapper.mapToResponse(doctor);
  }

  @Override
  public List<DoctorResp> findAll() {
    return doctorRepository.findAll().stream().map(mapper::mapToResponse).toList();
  }

  @Override
  public void delete(Long doctorId) {

    this.validateDoctor(doctorId);
    doctorRepository.deleteById(doctorId);

  }

  @Override
  public List<DoctorResp> findBySpeciality(Long specialityId) {

    specialityRepository.findById(specialityId)
            .orElseThrow(()->
                    new ResourceNotFoundException("Speciality", "id", specialityId, "SPECIALITY_NOT_FOUND"));
    return doctorRepository.findBySpeciality_SpecialityId(specialityId).stream().map(doctor -> mapper.mapToResponse(doctor.get())).toList();
  }

  private Doctor  validateDoctor(Long doctorId){
    return doctorRepository.findById(doctorId)
            .orElseThrow(()->
                    new ResourceNotFoundException("Doctor", "id", doctorId, "DOCTOR_NOT_FOUND"));

  }
}
