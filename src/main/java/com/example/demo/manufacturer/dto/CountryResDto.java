package com.example.demo.manufacturer.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class CountryResDto extends CountryDto{
    private Integer id;
}
