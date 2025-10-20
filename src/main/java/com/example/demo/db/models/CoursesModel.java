package com.example.demo.db.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cources")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoursesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "descroption", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    @OneToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RequestModel> request;
}
