/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;

import com.doctor.beans.speciality.SpecialityReq;
import com.doctor.beans.speciality.SpecialityResp;
import com.doctor.entities.Speciality;
import com.doctor.mapper.Mapper;
import com.doctor.mapper.MapperPatten;
import com.doctor.mapper.SpecialityMapper;
import com.doctor.repositories.SpecialityRepository;
import jakarta.persistence.EntityNotFoundException;
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
      return mapper.mapToResponse(specialityRepository.save(mapper.mapToEntity(specialityReq, MapperPatten.CREATE)));
  }

  @Override
  public SpecialityResp update(SpecialityReq specialityReq) {

    final var specialityId = specialityReq.specialityId();
    if (specialityId == null) {
      throw new IllegalArgumentException("Cannot update an entity without an ID");
    }
    return mapper.mapToResponse(specialityRepository.save(mapper.mapToEntity(specialityReq, MapperPatten.UPDATE)));
  }

  @Override
  public SpecialityResp find(Long specialityId) {
    return mapper.mapToResponse(specialityRepository.findById(specialityId).orElse(null));
  }

  @Override
  public List<SpecialityResp> findAll() {
        return specialityRepository.findAll().stream().map(mapper::mapToResponse).toList();
  }

  @Override
  public void delete(Long specialityId){
    specialityRepository.deleteById(specialityId);
  }
}
