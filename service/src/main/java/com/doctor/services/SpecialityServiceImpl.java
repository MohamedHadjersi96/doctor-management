/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;

import com.doctor.beans.doctor.DoctorResp;
import com.doctor.beans.speciality.SpecialityReq;
import com.doctor.beans.speciality.SpecialityResp;
import com.doctor.entities.Doctor;
import com.doctor.entities.Speciality;
import com.doctor.exceptions.ResourceNotFoundException;
import com.doctor.exceptions.SpecialityAlreadyExistException;
import com.doctor.mapper.Mapper;
import com.doctor.mapper.MapperPattern;
import com.doctor.repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityServiceImpl  implements SpecialityService{
  @Autowired
  private SpecialityRepository specialityRepository;

  @Autowired
  private Mapper<Speciality, SpecialityReq, SpecialityResp> mapper;

  @Autowired
  private Mapper<Doctor, ?, DoctorResp> doctorMapper;

  @Override
  public SpecialityResp create(SpecialityReq specialityReq) {

    specialityRepository.findByName(specialityReq.name())
            .ifPresent(existingSpeciality -> {
              throw new SpecialityAlreadyExistException("Speciality name " + specialityReq.name() + " Already Exists");
            });
    return mapper.mapToResponse(specialityRepository.save(mapper.mapToEntity(specialityReq, MapperPattern.CREATE)));
  }

  @Override
  public SpecialityResp update(SpecialityReq specialityReq) {

    this.validateSpeciality(specialityReq.specialityId());
    return mapper.mapToResponse(specialityRepository.save(mapper.mapToEntity(specialityReq, MapperPattern.UPDATE)));
  }

  @Override
  public SpecialityResp find(Long specialityId) {
    final Speciality speciality = this.validateSpeciality(specialityId);
    return mapper.mapToResponse(speciality);
  }

  @Override
  public List<SpecialityResp> findAll() {
        return specialityRepository.findAll().stream().map(mapper::mapToResponse).toList();
  }

  @Override
  public void delete(Long specialityId){

    this.validateSpeciality(specialityId);
    specialityRepository.deleteById(specialityId);
  }

  @Override
  public List<DoctorResp> findDoctorsBySpeciality(Long specialityId) {
    final Speciality speciality = this.validateSpeciality(specialityId);

    return speciality.getDoctors()
            .stream()
            .map(doctor -> doctorMapper.mapToResponse(doctor))
            .toList();
  }

  private Speciality  validateSpeciality(Long specialityId){
    return specialityRepository.findById(specialityId)
            .orElseThrow(()->
                    new ResourceNotFoundException("Speciality", "id", specialityId, "SPECIALITY_NOT_FOUND"));

  }
}
