package com.example.demo.db.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "requests")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "comment")
    private String comment;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "handled", nullable = false)
    private Boolean handled = false;
}
