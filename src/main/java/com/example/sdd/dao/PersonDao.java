package com.example.sdd.dao;

import com.example.sdd.entity.Person;

import java.util.List;

public interface PersonDao {

    List<Person> findAllCities();

    Person findById(int id);

    Person findByName(String name);

    Person create(Person person);

    Person update(Person person);

    void delete(int id);

}
