package com.example.demo.manufacturer.db.repositories;

import com.example.demo.manufacturer.db.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Integer> {
}
