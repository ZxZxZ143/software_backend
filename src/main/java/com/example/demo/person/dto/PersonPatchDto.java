package com.example.demo.person.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonPatchDto {
    private String name;

    private Integer age;

    private String surname;

    private Integer height;
}
