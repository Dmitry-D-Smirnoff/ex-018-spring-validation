package com.example.sdd.service;

import com.example.sdd.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllCities();

    Person getPersonById(int id);

    Person getPersonByName(String name);

    Person createPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(int id);

}
