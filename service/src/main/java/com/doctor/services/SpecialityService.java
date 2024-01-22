/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;

import com.doctor.entities.Speciality;

import java.util.List;

public interface SpecialityService {

  public Speciality createSpeciality(Speciality speciality);
  public Speciality updateSpeciality(Speciality speciality);
  public Speciality getSpeciality(Long specialityId);
  public List<Speciality> getAllSpecialities();
  public void deleteSpeciality(Long specialityId);


}
