/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.services;

import com.doctor.beans.patient.PatientReq;
import com.doctor.beans.patient.PatientResp;

import java.util.List;

public interface PatientService {

  PatientResp create(PatientReq req);
  PatientResp update(PatientReq req);
  PatientResp find(Long patientId);
  List<PatientResp> findAll();
  void delete(Long patientId);

}
