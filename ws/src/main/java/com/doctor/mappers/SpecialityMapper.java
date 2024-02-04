/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.mappers;

import com.doctor.beans.speciality.SpecialityReq;
import com.doctor.beans.speciality.SpecialityResp;
import com.doctor.entities.Speciality;

import java.util.ArrayList;
import java.util.List;

public class SpecialityMapper {

  public static Speciality toSpeciality(SpecialityReq specialityReq){

    return Speciality.builder()
            .specialityId(specialityReq.specialityId())
            .name(specialityReq.name())
            .description(specialityReq.description())
            .build();
  }

  public static SpecialityResp toResp(Speciality speciality){
    return SpecialityResp.builder()
             .specialityId(speciality.getSpecialityId())
             .name(speciality.getName())
             .description(speciality.getDescription())
             .creationDate(speciality.getCreationDate())
             .modificationDate(speciality.getModificationDate())
             .build();
  }

  public static List<SpecialityResp> toSpecialitiesResps(List<Speciality> specialities){

    final  List<SpecialityResp> specialitiesResps = new ArrayList<>();
    for(Speciality speciality : specialities){
      final SpecialityResp specialityResp = toResp(speciality);
      specialitiesResps.add(specialityResp);
    }
    return specialitiesResps;
  }
}
