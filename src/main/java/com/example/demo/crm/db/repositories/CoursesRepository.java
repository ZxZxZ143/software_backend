package com.example.demo.crm.db.repositories;

import com.example.demo.crm.db.models.CoursesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<CoursesModel, Long> {
}
