package com.example.demo.person.mappers;

import com.example.demo.person.dto.PetDto;
import com.example.demo.person.dto.PetResDto;
import com.example.demo.person.models.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {
    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    PetResDto toDto(Pet pet);

    Pet toEntity(PetDto petDto);

    List<PetResDto> toDtos(List<Pet> pets);
}
