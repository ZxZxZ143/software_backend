package com.example.demo.manufacturer.mappers;

import com.example.demo.manufacturer.db.models.Country;
import com.example.demo.manufacturer.dto.CountryDetailDto;
import com.example.demo.manufacturer.dto.CountryDto;
import com.example.demo.manufacturer.dto.CountryResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    Country toEntity(CountryDto countryDto);

    @Named("CountryToDto")
    CountryResDto toDto(Country country);

    @Mapping(source = "items", target = "items")
    CountryDetailDto toResDto(Country country);

    @Mapping(source = "items", target = "items")
    List<CountryResDto> toListDto(List<Country> country);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    void updateCountry(CountryDto countryDto, @MappingTarget Country country);
}
