package com.example.demo.manufacturer.db.repositories;

import com.example.demo.manufacturer.db.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Integer> {
}
