/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;



import com.doctor.beans.speciality.SpecialityReq;
import com.doctor.beans.speciality.SpecialityResp;

import java.util.List;

public interface SpecialityService {

  public SpecialityResp create(SpecialityReq req);
  public SpecialityResp update(SpecialityReq req);
  public SpecialityResp find(Long specialityId);
  public List<SpecialityResp> findAll();
  public void delete(Long specialityId);


}
