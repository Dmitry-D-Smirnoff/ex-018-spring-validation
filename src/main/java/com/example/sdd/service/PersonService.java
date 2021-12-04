package com.example.sdd.service;

import com.example.sdd.entity.PersonEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {

    List<PersonEntity> getAllPersons();

    PersonEntity getPersonById(int id);

    List<PersonEntity> getPersonByName(String name);

    PersonEntity createPerson(PersonEntity personEntity);

    PersonEntity updatePerson(PersonEntity personEntity);

    void deletePerson(int id);

}
