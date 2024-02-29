/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;

import com.doctor.beans.doctor.DoctorReq;
import com.doctor.beans.doctor.DoctorResp;
import java.util.List;

public interface DoctorService {

  DoctorResp create(DoctorReq req);
  DoctorResp update(DoctorReq req);
  DoctorResp find(Long doctorId);
  List<DoctorResp> findAll();
  void delete(Long doctorId);
  List<DoctorResp> findBySpeciality(Long specialityId);
}
