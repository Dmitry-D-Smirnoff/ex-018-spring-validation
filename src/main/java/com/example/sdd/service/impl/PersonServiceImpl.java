package com.example.sdd.service.impl;

import com.example.sdd.dao.PersonDao;
import com.example.sdd.entity.Person;
import com.example.sdd.service.PersonService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.example.sdd.validation.ExampleValidationUtils.allNotEmpty;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> getAllPersons() {
        return personDao.findAllPersons();
    }

    public Person getPersonById(int id) {
        if (!allNotEmpty(id, personDao.findById(id)))
            throw new EntityNotFoundException("No person found for id=" + id);

        return personDao.findById(id);
    }

    public List<Person> getPersonByName(String name) {
        return personDao.findByName(name);
    }

    public Person createPerson(Person person) {
        return personDao.create(person);
    }

    public Person updatePerson(Person person) {
        if (!allNotEmpty(person, person.getId(), personDao.findById(person.getId())))
            throw new EntityNotFoundException("No person found for id=" + person.getId());

        return personDao.update(person);
    }

    public void deletePerson(int id) {
        personDao.delete(id);
    }

}
