/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.controllers;

import com.doctor.beans.doctor.DoctorReq;
import com.doctor.beans.doctor.DoctorResp;
import com.doctor.services.DoctorService;
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
@RequestMapping("api/v1/doctor")
public class DoctorController {

  @Autowired
  private DoctorService doctorService;

  @PostMapping("/create")
  public ResponseEntity<Void> create(@RequestBody @Valid DoctorReq doctorReq, UriComponentsBuilder uriBuilder) {
    final var doctorResp = doctorService.create(doctorReq);
    final String uriString = uriBuilder.path("api/v1/doctor/{id}").buildAndExpand(doctorResp.doctorId()).toUriString();
    return ResponseEntity.created(UriComponentsBuilder.fromUriString(uriString).build().toUri()).build();
  }

  @PutMapping("/update")
  public ResponseEntity<DoctorResp> update(@RequestBody @Valid DoctorReq doctorReq) {
    final DoctorResp doctorResp = doctorService.update(doctorReq);
    return new ResponseEntity<>(doctorResp, HttpStatus.OK);
  }

  @GetMapping("{doctorId}")
  public ResponseEntity<DoctorResp> get(@PathVariable("doctorId") @NotNull(message = "empty doctorId") Long doctorId) {
    final DoctorResp doctorResp = doctorService.find(doctorId);
    return new ResponseEntity<>(doctorResp, HttpStatus.OK);
  }

  @GetMapping("/speciality/{specialityId}")
  public  ResponseEntity<List<DoctorResp>> getDoctorBySpeciality(@PathVariable("specialityId") @NotNull(message = "empty specialityId") Long specialityId) {
    final List<DoctorResp> specialitiesResp = doctorService.findBySpeciality(specialityId);
    return new ResponseEntity<>(specialitiesResp, HttpStatus.OK);
  }
  @GetMapping
  public ResponseEntity<List<DoctorResp>> getAll() {
    final List<DoctorResp> specialitiesResp = doctorService.findAll();
    return new ResponseEntity<>(specialitiesResp, HttpStatus.OK);
  }

  @DeleteMapping("{doctorId}")
  public ResponseEntity<?> delete(@PathVariable("doctorId") @NotNull(message = "empty doctorId") Long doctorId) {

    doctorService.delete(doctorId);
    return new ResponseEntity<>("Doctor deleted", HttpStatus.OK);
  }
}
