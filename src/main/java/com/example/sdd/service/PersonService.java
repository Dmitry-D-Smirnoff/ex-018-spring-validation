package com.example.sdd.service;

import com.example.sdd.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {

    List<Person> getAllPersons();

    Person getPersonById(int id);

    List<Person> getPersonByName(String name);

    Person createPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(int id);

}
