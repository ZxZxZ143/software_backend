package com.example.demo.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Service
public class PersonService {
    private PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
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
}
