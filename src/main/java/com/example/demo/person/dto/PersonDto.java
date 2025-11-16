package com.example.demo.person.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    @NotBlank
    @Size(min = 1, max = 40)
    private String name;

    private Integer age;

    @NotBlank
    @Size(min = 1, max = 40)
    private String surname;

    private Integer height;
}
