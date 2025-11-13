package com.example.demo.person;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    public Person toEntity(PersonDto personDto);

    public PersonDto toDto(Person person);

    public PersonResDto toResDto(Person person);

    public List<PersonResDto> toResDto(List<Person> persons);
}
