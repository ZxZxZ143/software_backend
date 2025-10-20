package com.example.demo.db.repositories;

import com.example.demo.db.models.CoursesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<CoursesModel, Long> {
}
