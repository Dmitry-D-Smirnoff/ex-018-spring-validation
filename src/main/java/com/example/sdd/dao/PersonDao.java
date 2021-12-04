package com.example.sdd.dao;

import com.example.sdd.entity.PersonEntity;

import java.util.List;

public interface PersonDao {

    List<PersonEntity> findAllPersons();

    PersonEntity findById(int id);

    List<PersonEntity> findByName(String name);

    PersonEntity create(PersonEntity personEntity);

    PersonEntity update(PersonEntity personEntity);

    void delete(int id);

}
