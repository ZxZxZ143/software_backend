package com.example.demo.person.services;

import com.example.demo.person.dto.PersonDto;
import com.example.demo.person.mappers.PersonMapper;
import com.example.demo.person.dto.PersonPatchDto;
import com.example.demo.person.dto.PersonResDto;
import com.example.demo.person.models.Person;
import com.example.demo.person.models.Pet;
import com.example.demo.person.repositories.PersonRepo;
import com.example.demo.person.repositories.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Service
public class PersonService {
    private PersonRepo personRepo;
    private PetRepo petRepo;

    @Autowired
    public PersonService(PersonRepo personRepo, PetRepo petRepo) {
        this.personRepo = personRepo;
        this.petRepo = petRepo;
    }

    public List<PersonResDto> getAllPersons() {
        return PersonMapper.INSTANCE.toResDto(personRepo.findAll());
    }

    public PersonResDto savePerson(PersonDto personDto) {
        Person person = PersonMapper.INSTANCE.toEntity(personDto);

        personRepo.save(person);
        return PersonMapper.INSTANCE.toResDto(person);
    }

    public PersonResDto updatePerson(Long id, PersonDto personDto) {
        Person person = personRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Person with id " + id + " does not exist"));

        person.setName(personDto.getName());
        person.setAge(personDto.getAge());
        person.setSurname(personDto.getSurname());
        person.setHeight(personDto.getHeight());

        personRepo.save(person);
        return PersonMapper.INSTANCE.toResDto(person);
    }

    public PersonResDto patchPerson(Long id, PersonPatchDto personDto) {
        Person person = personRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Person with id " + id + " does not exist"));

        Map<Supplier<Object>, Runnable> map = Map.of(
                personDto::getAge, () -> person.setAge(personDto.getAge()),
                personDto::getHeight, () -> person.setHeight(personDto.getHeight()),
                personDto::getSurname, () -> person.setSurname(personDto.getSurname()),
                personDto::getName, () -> person.setName(personDto.getName())
        );

        map.forEach((objectSupplier, runnable) -> {
            if (objectSupplier.get() != null) {
                runnable.run();
            }
        });

        personRepo.save(person);

        return PersonMapper.INSTANCE.toResDto(person);
    }

    public void deletePerson(Long id) {
        personRepo.deleteById(id);
    }

    public PersonResDto assignPetToPerson(Long personId, Long petId) {
        Person person = personRepo.findById(personId).orElseThrow(() -> new NoSuchElementException("Person with id " + personId + " does not exist"));
        Pet pet = petRepo.findById(petId).orElseThrow(() -> new NoSuchElementException("Pet with id " + petId + " does not exist"));

        pet.setPerson(person);
        petRepo.save(pet);

        return PersonMapper.INSTANCE.toResDto(person);
    }

    public PersonResDto assignParent(Long personId, Long parentId) {
        Person person = personRepo.findById(personId).orElseThrow(() -> new NoSuchElementException("Person with id " + personId + " does not exist"));
        Person parent = personRepo.findById(parentId).orElseThrow(() -> new NoSuchElementException("Parent with id " + parentId + " does not exist"));

        person.getParents().add(parent);

        personRepo.save(person);
        return PersonMapper.INSTANCE.toResDto(person);
    }
}
