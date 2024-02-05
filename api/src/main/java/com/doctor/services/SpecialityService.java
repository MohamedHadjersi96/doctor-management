/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;

import com.doctor.entities.Speciality;

import java.util.List;

public interface SpecialityService {

  public Speciality create(Speciality speciality);
  public Speciality update(Speciality speciality);
  public Speciality find(Long specialityId);
  public List<Speciality> findAll();
  public void delete(Long specialityId);


}
