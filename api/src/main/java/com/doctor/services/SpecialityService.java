/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;



import com.doctor.beans.doctor.DoctorResp;
import com.doctor.beans.speciality.SpecialityReq;
import com.doctor.beans.speciality.SpecialityResp;

import java.util.List;

public interface SpecialityService {

  SpecialityResp create(SpecialityReq req);
  SpecialityResp update(SpecialityReq req);
  SpecialityResp find(Long specialityId);
  List<SpecialityResp> findAll();
  void delete(Long specialityId);

  List<DoctorResp> findDoctorsBySpeciality(Long specialityId);


}
