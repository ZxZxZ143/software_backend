package com.example.demo.Items;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    int id;
    String title;
    String description;
    Float price;
}
