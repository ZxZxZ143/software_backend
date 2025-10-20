package com.example.demo.db.repositories;

import com.example.demo.db.models.OperatorsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorsRepository extends JpaRepository<OperatorsModel, Long> {
}
