package com.example.sdd.service.impl;

import com.example.sdd.dao.PersonDao;
import com.example.sdd.entity.Person;
import com.example.sdd.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> getAllCities() {
        return personDao.findAllCities();
    }

    public Person getPersonById(int id) {
        return personDao.findById(id);
    }

    public Person getPersonByName(String name) {
        return personDao.findByName(name);
    }

    public Person createPerson(Person person) {
        return personDao.create(person);
    }

    public Person updatePerson(Person person) {
        return personDao.update(person);
    }

    public void deletePerson(int id) {
        personDao.delete(id);
    }

}
