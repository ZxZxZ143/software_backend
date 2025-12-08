package com.example.demo.manufacturer.controllers;

import com.example.demo.manufacturer.dto.CountryDetailDto;
import com.example.demo.manufacturer.dto.CountryDto;
import com.example.demo.manufacturer.dto.CountryResDto;
import com.example.demo.manufacturer.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<CountryResDto>> getCountries() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(countryService.getAllCountries());
    }

    @GetMapping("{id}")
    public ResponseEntity<CountryDetailDto> getCountryById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.getCountryById(id));
    }

    @PostMapping
    public ResponseEntity<CountryDetailDto> createCountry(@RequestBody CountryDto countryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.createCountry(countryDto));
    }
}
