package com.example.demo.manufacturer.service;

import com.example.demo.manufacturer.db.models.Country;
import com.example.demo.manufacturer.db.repositories.CountryRepo;
import com.example.demo.manufacturer.dto.CountryDto;
import com.example.demo.manufacturer.dto.CountryResDto;
import com.example.demo.manufacturer.mappers.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepo countryRepo;
    private final CountryMapper countryMapper;

    public List<CountryResDto> getAllCountries() {
        return countryMapper.toListDto(countryRepo.findAll());
    }

    public CountryResDto getCountryById(Integer id) {
        return countryMapper
                .toResDto(countryRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Country with id " + id + " not found!")));
    }

    public CountryResDto createCountry(CountryDto countryDto) {
        Country country = countryMapper.toEntity(countryDto);

        countryRepo.save(country);

        return countryMapper.toResDto(country);
    }
}
