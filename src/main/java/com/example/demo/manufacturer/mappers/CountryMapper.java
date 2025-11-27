package com.example.demo.manufacturer.mappers;

import com.example.demo.manufacturer.db.models.Country;
import com.example.demo.manufacturer.dto.CountryDto;
import com.example.demo.manufacturer.dto.CountryResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    Country toEntity(CountryDto countryDto);

    @Named("CountryToDto")
    CountryDto toDto(Country country);

    @Mapping(source = "items", target = "items")
    CountryResDto toResDto(Country country);

    @Mapping(source = "items", target = "items")
    List<CountryResDto> toListDto(List<Country> country);
}
