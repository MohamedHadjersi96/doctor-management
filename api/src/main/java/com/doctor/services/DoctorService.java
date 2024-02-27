/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;

import com.doctor.beans.doctor.DoctorReq;
import com.doctor.beans.doctor.DoctorResp;
import java.util.List;

public interface DoctorService {

  public DoctorResp create(DoctorReq req);
  public DoctorResp update(DoctorReq req);
  public DoctorResp find(Long doctorId);
  public List<DoctorResp> findAll();
  public void delete(Long doctorId);
  public List<DoctorResp> findBySpeciality(Long specialityId);
}
