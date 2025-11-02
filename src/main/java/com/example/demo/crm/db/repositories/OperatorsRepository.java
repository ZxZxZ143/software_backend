package com.example.demo.crm.db.repositories;

import com.example.demo.crm.db.models.OperatorsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorsRepository extends JpaRepository<OperatorsModel, Long> {
}
