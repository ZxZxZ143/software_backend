package com.example.demo.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResDto extends PersonDto {
    private Long id;
    private List<PetResDto> pet;
    private List<ParentDto> parents;
}
