package com.example.demo.person.controllers;

import com.example.demo.person.dto.PetDto;
import com.example.demo.person.dto.PetResDto;
import com.example.demo.person.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping()
    public ResponseEntity<PetResDto> create(@RequestBody PetDto petDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(petService.create(petDto));
    }
}
