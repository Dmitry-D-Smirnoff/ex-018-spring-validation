package com.example.sdd.service;

import com.example.sdd.entity.Person;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@Service
public interface PersonService {

    List<Person> getAllPersons();

    Person getPersonById(int id);

    List<Person> getPersonByName(String name);

    Person createPerson(@Valid Person person);

    Person updatePerson(@Valid Person person);

    void deletePerson(int id);

}
