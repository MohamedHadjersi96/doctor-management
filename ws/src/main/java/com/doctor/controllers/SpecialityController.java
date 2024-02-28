/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.controllers;


import com.doctor.beans.doctor.DoctorResp;
import com.doctor.beans.speciality.SpecialityReq;
import com.doctor.beans.speciality.SpecialityResp;
import com.doctor.services.SpecialityService;
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
@RequestMapping("api/v1/speciality")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid SpecialityReq specialityReq, UriComponentsBuilder uriBuilder) {
        final var specialityResp = specialityService.create(specialityReq);
        String uriString = uriBuilder.path("api/v1/speciality/{id}").buildAndExpand(specialityResp.specialityId()).toUriString();

//    return new ResponseEntity<>(specialityResp, HttpStatus.CREATED);
        return ResponseEntity.created(UriComponentsBuilder.fromUriString(uriString).build().toUri()).build();
    }

    @PutMapping("/update")
    public ResponseEntity<SpecialityResp> update(@RequestBody @Valid SpecialityReq specialityReq) {
        final SpecialityResp specialityResp = specialityService.update(specialityReq);
        return new ResponseEntity<>(specialityResp, HttpStatus.OK);
    }

    @GetMapping("{specialityId}")
    public ResponseEntity<SpecialityResp> get(@PathVariable("specialityId") @NotNull(message = "empty id") Long specialityId) {
        final SpecialityResp specialityResp = specialityService.find(specialityId);
        return new ResponseEntity<>(specialityResp, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SpecialityResp>> getAll() {
        final List<SpecialityResp> specialitiesResp = specialityService.findAll();
        return new ResponseEntity<>(specialitiesResp, HttpStatus.OK);
    }

    @GetMapping("/doctors/{specialityId}")
    public ResponseEntity<List<DoctorResp>> getDoctorsBySpeciality(@PathVariable("specialityId") @NotNull(message = "empty id") Long specialityId) {
        final List<DoctorResp> doctorsResp = specialityService.findDoctorsBySpeciality(specialityId);
        return new ResponseEntity<>(doctorsResp, HttpStatus.OK);
    }

    @DeleteMapping("{specialityId}")
    public ResponseEntity<?> delete(@PathVariable("specialityId") @NotNull(message = "empty specialityId") Long specialityId) {

        specialityService.delete(specialityId);
        return new ResponseEntity<>("Speciality deleted", HttpStatus.OK);
    }
}
