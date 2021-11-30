package com.example.sdd.dao.impl;

import com.example.sdd.dao.PersonDao;
import com.example.sdd.entity.Person;
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
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findAllPersons() {
        return entityManager.createNamedQuery("Person.findAll", Person.class).getResultList();
    }

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public List<Person> findByName(String name) {
        return entityManager.createNamedQuery("Person.findByName", Person.class)
                .setParameter("name", name).getResultList();
    }

    public Person create(Person Person) {
        entityManager.persist(Person);
        return Person;
    }

    public Person update(Person person) {
        //TODO: Перенести проверку в валидатор сущности?
        if (entityManager.find(Person.class, person.getId()) == null)
            throw new EntityNotFoundException("No Person found for id=" + person.getId());
        return entityManager.merge(person);
    }

    public void delete(int id) {
        Person Person = entityManager.find(Person.class, id);
        if (Person != null) {
            entityManager.remove(Person);
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

        if (result == null || result.isEmpty())
            return null;
        else
            return result.get(0);
    }

}
