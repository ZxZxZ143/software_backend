package com.example.demo.person.repositories;

import com.example.demo.person.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepo extends JpaRepository<Pet, Long> {
}
