/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;

import com.doctor.beans.speciality.SpecialityReq;
import com.doctor.beans.speciality.SpecialityResp;
import com.doctor.entities.Speciality;
import com.doctor.exceptions.ResourceNotFoundException;
import com.doctor.exceptions.SpecialityAlreadyExistException;
import com.doctor.mapper.Mapper;
import com.doctor.mapper.MapperPatten;
import com.doctor.repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialityServiceImpl  implements SpecialityService{
  @Autowired
  private SpecialityRepository specialityRepository;

  @Autowired
  private Mapper<Speciality, SpecialityReq, SpecialityResp> mapper;

  @Override
  public SpecialityResp create(SpecialityReq specialityReq) {

    final Optional speciality = specialityRepository.findByName(specialityReq.name());
    if(speciality.isPresent()){
      throw new SpecialityAlreadyExistException("Speciality name "+specialityReq.name()+" Already Exists");
    }
      return mapper.mapToResponse(specialityRepository.save(mapper.mapToEntity(specialityReq, MapperPatten.CREATE)));
  }

  @Override
  public SpecialityResp update(SpecialityReq specialityReq) {

    this.validateSpeciality(specialityReq.specialityId());
    return mapper.mapToResponse(specialityRepository.save(mapper.mapToEntity(specialityReq, MapperPatten.UPDATE)));
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

  private Speciality  validateSpeciality(Long specialityId){
    return specialityRepository.findById(specialityId)
            .orElseThrow(()->
                    new ResourceNotFoundException("Speciality", "id", specialityId, "SPECIALITY_NOT_FOUND"));

  }
}
