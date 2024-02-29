/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.controllers;


import com.doctor.beans.patient.PatientReq;
import com.doctor.beans.patient.PatientResp;
import com.doctor.services.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("api/v1/patient")
public class PatientController {

  @Autowired
  private PatientService patientService;

  @PostMapping("/create")
  public ResponseEntity<Void> create(@RequestBody @Valid PatientReq patientReq, UriComponentsBuilder uriBuilder) {
    final var patientResp = patientService.create(patientReq);
    final String uriString = uriBuilder.path("api/v1/patient/{id}").buildAndExpand(patientResp.patientId()).toUriString();
    return ResponseEntity.created(UriComponentsBuilder.fromUriString(uriString).build().toUri()).build();
  }

  @PutMapping("/update")
  public ResponseEntity<PatientResp> update(@RequestBody @Valid PatientReq patientReq) {
    final PatientResp patientResp = patientService.update(patientReq);
    return new ResponseEntity<>(patientResp, HttpStatus.OK);
  }

  @GetMapping("{patientId}")
  public ResponseEntity<PatientResp> get(@PathVariable("patientId") @NotNull(message = "empty doctorId") Long patientId) {
    final PatientResp patientResp = patientService.find(patientId);
    return new ResponseEntity<>(patientResp, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<PatientResp>> getAll() {
    final List<PatientResp> patientsResp = patientService.findAll();
    return new ResponseEntity<>(patientsResp, HttpStatus.OK);
  }

  @DeleteMapping("{patientId}")
  public ResponseEntity<?> delete(@PathVariable("patientId") @NotNull(message = "empty doctorId") Long patientId) {
    patientService.delete(patientId);
    return new ResponseEntity<>("Patient deleted", HttpStatus.OK);
  }
}
