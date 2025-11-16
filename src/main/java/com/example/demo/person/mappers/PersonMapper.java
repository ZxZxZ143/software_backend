package com.example.demo.person.mappers;

import com.example.demo.person.dto.ParentDto;
import com.example.demo.person.dto.PersonDto;
import com.example.demo.person.dto.PersonResDto;
import com.example.demo.person.models.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    public Person toEntity(PersonDto personDto);

    public PersonDto toDto(Person person);

    @Mapping(source = "parents", target = "parents")
    public PersonResDto toResDto(Person person);

    public List<PersonResDto> toResDto(List<Person> persons);

    @Mapping(source = "id", target = "parent_id")
    public ParentDto toParentDto(Person person);
}
