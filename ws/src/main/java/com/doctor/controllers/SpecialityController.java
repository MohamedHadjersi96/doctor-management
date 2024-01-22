/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.controllers;


import com.doctor.beans.speciality.SpecialityReq;
import com.doctor.beans.speciality.SpecialityResp;
import com.doctor.entities.Speciality;
import com.doctor.mappers.SpecialityMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.doctor.services.SpecialityService;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("api/v1/speciality")
public class SpecialityController {

  private SpecialityService specialityService;

  @PostMapping("/create")
  public ResponseEntity<SpecialityResp> createSpeciality(@RequestBody @Valid SpecialityReq specialityReq){
    final Speciality speciality = SpecialityMapper.toSpeciality(specialityReq);
    final Speciality savedSpeciality = specialityService.createSpeciality(speciality);
    final SpecialityResp specialityResp = SpecialityMapper.toResp(savedSpeciality);
    return new ResponseEntity<>(specialityResp, HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<SpecialityResp> updateSpeciality(@RequestBody @Valid SpecialityReq specialityReq){
    final Speciality speciality = SpecialityMapper.toSpeciality(specialityReq);
    final Speciality updatedSpeciality = specialityService.updateSpeciality(speciality);
    final SpecialityResp specialityResp = SpecialityMapper.toResp(updatedSpeciality);
    return new ResponseEntity<>(specialityResp, HttpStatus.OK);
  }

  @GetMapping("{specialityId}")
  public ResponseEntity<SpecialityResp> getSpeciality(@PathVariable @NotNull(message = "empty specialityId") Long specialityId){
    final Speciality speciality = specialityService.getSpeciality(specialityId);
    final SpecialityResp specialityResp = SpecialityMapper.toResp(speciality);
    return new ResponseEntity<>(specialityResp, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<SpecialityResp>> getAllSpecialities(){
    final List<Speciality> specialities = specialityService.getAllSpecialities();
    final List<SpecialityResp> specialitiesResp = SpecialityMapper.toSpecialitiesResps(specialities);
    return new ResponseEntity<>(specialitiesResp, HttpStatus.OK);
  }
  @DeleteMapping("{specialityId}")
  public ResponseEntity<?> deleteSpeciality(@PathVariable @NotNull(message = "empty specialityId") Long specialityId){

    final Speciality speciality = specialityService.getSpeciality(specialityId);
    if(speciality == null)
      return new ResponseEntity<>("Speciality not found", HttpStatus.NOT_FOUND);

    specialityService.deleteSpeciality(specialityId);

    return new ResponseEntity<>("Speciality deleted", HttpStatus.OK);
  }
}
