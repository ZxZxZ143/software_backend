package com.example.demo.db.repositories;

import com.example.demo.db.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<RequestModel, Long> {
    public RequestModel findById(long id);
}
