/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.controllers;


import com.doctor.beans.speciality.SpecialityReq;
import com.doctor.beans.speciality.SpecialityResp;
import com.doctor.services.SpecialityService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
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

    @GetMapping("{id}")
    public ResponseEntity<SpecialityResp> get(@PathVariable @NotNull(message = "empty id") Long id) {
        final SpecialityResp specialityResp = specialityService.find(id);
        return new ResponseEntity<>(specialityResp, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SpecialityResp>> getAll() {
        final List<SpecialityResp> specialitiesResp = specialityService.findAll();
        return new ResponseEntity<>(specialitiesResp, HttpStatus.OK);
    }

    @DeleteMapping("{specialityId}")
    public ResponseEntity<?> delete(@PathVariable @NotNull(message = "empty specialityId") Long specialityId) {
        try {
            specialityService.delete(specialityId);
            return new ResponseEntity<>("Speciality deleted", HttpStatus.OK);
            // ici on doit créer une autre exception validation exception par exemple
        } catch (Exception e) {
            return new ResponseEntity<>("Speciality with id " + specialityId + " not found", HttpStatus.NOT_FOUND);
        }

    }
}
