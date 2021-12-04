package com.example.sdd.service.impl;

import com.example.sdd.dao.PersonDao;
import com.example.sdd.entity.PersonEntity;
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

    public List<PersonEntity> getAllPersons() {
        return personDao.findAllPersons();
    }

    public PersonEntity getPersonById(int id) {
        if (!allNotEmpty(id, personDao.findById(id)))
            throw new EntityNotFoundException("No person found for id=" + id);

        return personDao.findById(id);
    }

    public List<PersonEntity> getPersonByName(String name) {
        return personDao.findByName(name);
    }

    public PersonEntity createPerson(PersonEntity personEntity) {
        return personDao.create(personEntity);
    }

    public PersonEntity updatePerson(PersonEntity personEntity) {
        if (!allNotEmpty(personEntity, personEntity.getId(), personDao.findById(personEntity.getId())))
            throw new EntityNotFoundException("No personEntity found for id=" + personEntity.getId());

        return personDao.update(personEntity);
    }

    public void deletePerson(int id) {
        personDao.delete(id);
    }

}
