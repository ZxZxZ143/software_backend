package com.example.demo.person;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonResDto>> getAllPersons() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.getAllPersons());
    }

    @PostMapping()
    public ResponseEntity<PersonResDto> savePerson(@RequestBody @Valid PersonDto personDto) {
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personService.savePerson(personDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResDto> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.updatePerson(id, personDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PersonResDto> patchPerson(@PathVariable Long id, @RequestBody PersonPatchDto personDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.patchPerson(id, personDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
