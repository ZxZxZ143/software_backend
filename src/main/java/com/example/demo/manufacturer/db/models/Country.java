package com.example.demo.manufacturer.db.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 25)
    @NotNull
    @Column(name = "code", nullable = false, length = 25)
    private String code;

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private List<Item> items;
}