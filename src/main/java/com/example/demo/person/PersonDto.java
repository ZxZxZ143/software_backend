package com.example.demo.person;

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

    @NotBlank
    private Integer age;

    @NotBlank
    @Size(min = 1, max = 40)
    private String surname;

    @NotBlank
    private Integer height;
}
