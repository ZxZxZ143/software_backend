package com.example.demo.person;

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
