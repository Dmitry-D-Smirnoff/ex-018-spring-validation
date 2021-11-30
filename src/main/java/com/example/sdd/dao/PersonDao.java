package com.example.sdd.dao;

import com.example.sdd.entity.Person;

import java.util.List;

public interface PersonDao {

    List<Person> findAllPersons();

    Person findById(int id);

    List<Person> findByName(String name);

    Person create(Person person);

    Person update(Person person);

    void delete(int id);

}
