package com.example.demo;

import com.example.demo.manufacturer.db.models.Country;
import com.example.demo.manufacturer.db.models.Item;
import com.example.demo.manufacturer.db.repositories.CountryRepo;
import com.example.demo.manufacturer.dto.CountryDetailDto;
import com.example.demo.manufacturer.dto.CountryDto;
import com.example.demo.manufacturer.dto.CountryResDto;
import com.example.demo.manufacturer.dto.ItemResDto;
import com.example.demo.manufacturer.mappers.CountryMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CountryMapperTest {
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private CountryRepo countryRepo;

    @Test
    @Transactional
    public void entityToDto(){
        Country country = countryRepo.findById(1).orElse(null);

        CountryResDto countryResDto = countryMapper.toDto(country);

        Assertions.assertNotNull(countryResDto);
        Assertions.assertNotNull(countryResDto.getId());
        Assertions.assertNotNull(countryResDto.getName());
        Assertions.assertNotNull(countryResDto.getCode());
        Assertions.assertNotNull(country);
        Assertions.assertEquals(country.getCode(), countryResDto.getCode());
        Assertions.assertEquals(country.getName(), countryResDto.getName());
    }

    @Test
    @Transactional
    public void dtoToEntity(){
        CountryDto countryDto = new CountryDto("name", "code");

        Country country = countryMapper.toEntity(countryDto);

        Assertions.assertNotNull(country);

        countryRepo.save(country);

        Assertions.assertNotNull(country.getId());
        Assertions.assertNotNull(country.getName());
        Assertions.assertNotNull(country.getCode());
        Assertions.assertEquals(countryDto.getName(), country.getName());
        Assertions.assertEquals(countryDto.getCode(), country.getCode());
    }

    @Test
    @Transactional
    public void entityToDetailDto() {
        Country country = countryRepo.findById(1).orElse(null);

        Assertions.assertNotNull(country);

        CountryDetailDto countryDetailDto = countryMapper.toResDto(country);

        Assertions.assertNotNull(countryDetailDto);
        Assertions.assertNotNull(countryDetailDto.getId());
        Assertions.assertNotNull(countryDetailDto.getName());
        Assertions.assertNotNull(countryDetailDto.getCode());
        Assertions.assertNotNull(countryDetailDto.getItems());
        Assertions.assertEquals(countryDetailDto.getCode(), country.getCode());
        Assertions.assertEquals(countryDetailDto.getName(), country.getName());

        for (int i = 0; i < countryDetailDto.getItems().size(); i++) {
            Item item = country.getItems().get(i);
            ItemResDto itemDto = countryDetailDto.getItems().get(i);

            Assertions.assertNotNull(itemDto);
            Assertions.assertNotNull(itemDto.getId());
            Assertions.assertNotNull(itemDto.getName());
            Assertions.assertNotNull(itemDto.getQuantity());
            Assertions.assertNotNull(itemDto.getPrice());
            Assertions.assertEquals(item.getId(), itemDto.getId());
            Assertions.assertEquals(item.getName(), itemDto.getName());
            Assertions.assertEquals(item.getPrice(), itemDto.getPrice());
            Assertions.assertEquals(item.getQuantity(), itemDto.getQuantity());
        }
    }

    @Test
    @Transactional
    public void ListEntityToListDto() {
        List<Country>  countryList = countryRepo.findAll();
        Assertions.assertNotNull(countryList);

        List<CountryResDto> countryResDtos = countryMapper.toListDto(countryList);

        Assertions.assertNotNull(countryResDtos);

        for (int i = 0; i < countryList.size(); i++) {
            Country country = countryList.get(i);
            CountryResDto countryResDto = countryResDtos.get(i);

            Assertions.assertNotNull(countryResDto);
            Assertions.assertNotNull(countryResDto.getId());
            Assertions.assertNotNull(countryResDto.getName());
            Assertions.assertNotNull(countryResDto.getCode());
            Assertions.assertEquals(country.getId(), countryResDto.getId());
            Assertions.assertEquals(country.getName(), countryResDto.getName());
            Assertions.assertEquals(country.getCode(), countryResDto.getCode());
        }
    }
}
