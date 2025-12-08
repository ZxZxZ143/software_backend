package com.example.demo;

import com.example.demo.manufacturer.db.models.Country;
import com.example.demo.manufacturer.dto.CountryDetailDto;
import com.example.demo.manufacturer.dto.CountryDto;
import com.example.demo.manufacturer.dto.CountryResDto;
import com.example.demo.manufacturer.service.CountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CountyServiceTest {
    @Autowired
    private CountryService countryService;

    @Test
    public void getAllCountries() {
        List<CountryResDto> countries = countryService.getAllCountries();

        Assertions.assertNotNull(countries);
        countries.forEach(countryResDto -> {
            Assertions.assertNotNull(countryResDto.getName());
            Assertions.assertNotNull(countryResDto.getCode());
            Assertions.assertNotNull(countryResDto.getId());
        });
    }

    @Test
    public void createCountry() {
        CountryDto country = new CountryDto("name", "code");

        CountryDetailDto countryDetailDto = countryService.createCountry(country);

        Assertions.assertNotNull(countryDetailDto);
        Assertions.assertNotNull(countryDetailDto.getId());
        Assertions.assertNotNull(countryDetailDto.getName());
        Assertions.assertNotNull(countryDetailDto.getCode());
    }

    @Test
    public void getCountryById() {
        CountryDetailDto countryDetailDto = countryService.getCountryById(2);
        Assertions.assertNotNull(countryDetailDto);
        Assertions.assertNotNull(countryDetailDto.getName());
        Assertions.assertNotNull(countryDetailDto.getCode());
        Assertions.assertNotNull(countryDetailDto.getItems());
    }

    @Test
    public void updateCountry() {
        CountryDto countryDto = new CountryDto("name", "code");

        CountryDetailDto updatedCountryDetailDto = countryService.updateCountry(2, countryDto);

        Assertions.assertNotNull(updatedCountryDetailDto);
        Assertions.assertNotNull(updatedCountryDetailDto.getName());
        Assertions.assertNotNull(updatedCountryDetailDto.getCode());
        Assertions.assertNotNull(updatedCountryDetailDto.getItems());
        Assertions.assertEquals(countryDto.getName(), updatedCountryDetailDto.getName());
        Assertions.assertEquals(countryDto.getCode(), updatedCountryDetailDto.getCode());
    }

    @Test
    public void deleteCountry() {
        countryService.deleteCountry(1);
    }
}
