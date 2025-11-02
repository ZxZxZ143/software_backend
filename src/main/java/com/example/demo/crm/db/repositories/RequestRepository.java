package com.example.demo.crm.db.repositories;

import com.example.demo.crm.db.models.CoursesModel;
import com.example.demo.crm.db.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<RequestModel, Long> {
}
