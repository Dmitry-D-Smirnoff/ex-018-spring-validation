package com.example.sdd.dao.impl;

import com.example.sdd.dao.CountryDao;
import com.example.sdd.entity.Country;
import com.example.sdd.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CountryDaoImpl implements CountryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Country> findAllCountries() {
        return entityManager.createNamedQuery("Country.findAll", Country.class).getResultList();
    }

    public Country findById(int id) {
        return entityManager.find(Country.class, id);
    }

    public List<Country> findByName(String name) {
        return entityManager.createNamedQuery("Country.findByName", Country.class)
                .setParameter("name", name).getResultList();
    }

    public Country create(Country country) {
        entityManager.persist(country);
        return country;
    }

    public Country update(Country country) {
        for (Person person : country.getPersons()) {
            Person existingPerson = entityManager.find(Person.class, person.getId());
            if (existingPerson != null) {
                existingPerson.setPersonName(person.getPersonName());
                entityManager.merge(existingPerson);
            } else {
                entityManager.persist(person);
            }
        }
        return entityManager.merge(country);
    }

    public void delete(int id) {
        Country country = entityManager.find(Country.class, id);
        if (country != null) {
            entityManager.remove(country);
        }
    }

}
