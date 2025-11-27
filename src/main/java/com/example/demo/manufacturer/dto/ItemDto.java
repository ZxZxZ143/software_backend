package com.example.demo.manufacturer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String name;

    private Integer price;

    private Integer quantity;

    private Integer manufacturerId;
}
