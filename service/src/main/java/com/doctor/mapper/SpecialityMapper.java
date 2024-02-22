package com.doctor.mapper;

import com.doctor.beans.speciality.SpecialityReq;
import com.doctor.beans.speciality.SpecialityResp;
import com.doctor.entities.Speciality;
import com.doctor.repositories.SpecialityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecialityMapper implements Mapper <Speciality, SpecialityReq, SpecialityResp> {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public Speciality mapToEntity(SpecialityReq request, MapperPatten pattern) {
    //il nous manque une couche validation (ex : objet avec clé fonctionnelle s'il existe on ne doit pas créer
    // mais lancer une 400 au niveau du controleur)

        if(MapperPatten.CREATE == pattern){
            return Speciality.builder()
                    .name(request.name())
                    .description(request.description())
                    .build();
        } else {
            final var existingSpeciality = specialityRepository.findById(request.specialityId())
                    .orElseThrow(() -> new EntityNotFoundException("Speciality with ID " + request.specialityId() + " not found"));
            existingSpeciality.setName(request.name());
            existingSpeciality.setDescription(request.description());
            return existingSpeciality;
        }
    }

    @Override
    public SpecialityResp mapToResponse(Speciality entity) {

        return SpecialityResp.builder()
                .specialityId(entity.getSpecialityId())
                .name(entity.getName())
                .description(entity.getDescription())
                .creationDate(entity.getCreationDate())
                .modificationDate(entity.getModificationDate())
                .build();
    }
}
