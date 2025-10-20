package com.example.demo.db.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Column(name = "comment")
    private String comment;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "handled", nullable = false)
    private Boolean handled = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private CoursesModel courses;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "operators_on_request",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "operator_id")
    )
    private List<OperatorsModel> operators;
}
