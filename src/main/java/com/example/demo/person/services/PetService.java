package com.example.demo.person.services;

import com.example.demo.person.dto.PetDto;
import com.example.demo.person.dto.PetResDto;
import com.example.demo.person.mappers.PetMapper;
import com.example.demo.person.models.Pet;
import com.example.demo.person.repositories.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private final PetRepo petRepo;

    @Autowired
    public PetService(PetRepo petRepo) {
        this.petRepo = petRepo;
    }

    public PetResDto create(PetDto petDto) {
        Pet pet = PetMapper.INSTANCE.toEntity(petDto);

        petRepo.save(pet);

        return PetMapper.INSTANCE.toDto(pet);
    }
}
