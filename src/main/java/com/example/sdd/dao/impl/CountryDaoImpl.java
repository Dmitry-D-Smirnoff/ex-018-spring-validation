package com.example.sdd.dao.impl;

import com.example.sdd.dao.CountryDao;
import com.example.sdd.entity.Person;
import com.example.sdd.entity.Country;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
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

    public Country findByName(String name) {
        return entityManager.createNamedQuery("Country.findByName", Country.class)
                .setParameter("name", name).getSingleResult();
    }

    public Country create(Country country) {
        entityManager.persist(country);
        return country;
    }

    public Country update(Country country) {
        //TODO: Перенести проверку в валидатор сущности?
        if (entityManager.find(Country.class, country.getId()) == null)
            throw new EntityNotFoundException("No country found for id=" + country.getId());
        for (Person person : country.getCities()) {
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

    private Person findPersonByName(String personName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        ParameterExpression<String> paramName = criteriaBuilder.parameter(String.class);
        CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
        query
                .select(query.from(Person.class))
                .where(criteriaBuilder.equal(query.from(Person.class).get("personName"), paramName));

        List<Person> result = entityManager.createQuery(query).setParameter(paramName, personName).getResultList();
        // Equivalent to:
        // List<Person> result = entityManager.createQuery("SELECT s FROM Person s WHERE s.personName = :personName", Person.class).setParameter("personName", personName).getResultList();

        if (result == null || result.isEmpty())
            return null;
        else
            return result.get(0);
    }

}
