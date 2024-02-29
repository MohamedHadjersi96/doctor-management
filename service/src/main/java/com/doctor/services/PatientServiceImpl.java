/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;

import com.doctor.beans.patient.PatientReq;
import com.doctor.beans.patient.PatientResp;
import com.doctor.entities.Patient;
import com.doctor.exceptions.DoctorAlreadyExistException;
import com.doctor.exceptions.ResourceNotFoundException;
import com.doctor.mapper.Mapper;
import com.doctor.mapper.MapperPattern;
import com.doctor.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private Mapper<Patient, PatientReq, PatientResp> mapper;

  @Override
  public PatientResp create(PatientReq patientReq) {
    patientRepository.findByEmail(patientReq.email())
            .ifPresent(existingSpeciality -> {
              throw new DoctorAlreadyExistException("Doctor email " + patientReq.email() + " Already Exists");
            });
    return mapper.mapToResponse(patientRepository.save(mapper.mapToEntity(patientReq, MapperPattern.CREATE)));
  }

  @Override
  public PatientResp update(PatientReq patientReq) {
    this.validatePatient(patientReq.patientId());
    return mapper.mapToResponse(patientRepository.save(mapper.mapToEntity(patientReq, MapperPattern.UPDATE)));
  }

  @Override
  public PatientResp find(Long patientId) {
    final Patient patient = this.validatePatient(patientId);
    return mapper.mapToResponse(patient);
  }

  @Override
  public List<PatientResp> findAll() {
    return patientRepository.findAll().stream().map(mapper::mapToResponse).toList();

  }

  @Override
  public void delete(Long patientId) {
    this.validatePatient(patientId);
    patientRepository.deleteById(patientId);
  }

  private Patient validatePatient(Long patientId){
    return patientRepository.findById(patientId)
            .orElseThrow(()->
                    new ResourceNotFoundException("Patient", "id", patientId, "PATIENT_NOT_FOUND"));

  }
}
