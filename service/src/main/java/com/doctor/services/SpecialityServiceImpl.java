/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;

import com.doctor.entities.Speciality;
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

  @Override
  public Speciality create(Speciality speciality) {
    final Speciality savedSpeciality = specialityRepository.save(speciality);
    return savedSpeciality;
  }

  @Override
  public Speciality update(Speciality speciality) {

    final Long specialityId = speciality.getSpecialityId();
    if (specialityId == null) {
      throw new IllegalArgumentException("Cannot update an entity without an ID");
    }

    final Optional<Speciality> existingSpecialityOptional = specialityRepository.findById(specialityId);
    if (existingSpecialityOptional.isPresent()) {
      final Speciality existingSpeciality = existingSpecialityOptional.get();

      existingSpeciality.setName(speciality.getName());
      existingSpeciality.setDescription(speciality.getDescription());

      final Speciality savedSpeciality = specialityRepository.save(existingSpeciality);

      return savedSpeciality;
    } else {
      throw new EntityNotFoundException("Speciality with ID " + specialityId + " not found");
    }

  }

  @Override
  public Speciality find(Long specialityId) {
    final Optional<Speciality> speciality = specialityRepository.findById(specialityId);
    return speciality.orElse(null);
  }

  @Override
  public List<Speciality> findAll() {
    return specialityRepository.findAll();
  }

  @Override
  public void delete(Long specialityId){
    specialityRepository.deleteById(specialityId);

  }
}
