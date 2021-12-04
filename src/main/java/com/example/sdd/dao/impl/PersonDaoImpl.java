package com.example.sdd.dao.impl;

import com.example.sdd.dao.PersonDao;
import com.example.sdd.entity.PersonEntity;
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

    public List<PersonEntity> findAllPersons() {
        return entityManager.createNamedQuery("PersonEntity.findAll", PersonEntity.class).getResultList();
    }

    public PersonEntity findById(int id) {
        return entityManager.find(PersonEntity.class, id);
    }

    public List<PersonEntity> findByName(String name) {
        return entityManager.createNamedQuery("PersonEntity.findByName", PersonEntity.class)
                .setParameter("name", name).getResultList();
    }

    public PersonEntity create(PersonEntity PersonEntity) {
        entityManager.persist(PersonEntity);
        return PersonEntity;
    }

    public PersonEntity update(PersonEntity personEntity) {
        //TODO: Перенести проверку в валидатор сущности?
        if (entityManager.find(PersonEntity.class, personEntity.getId()) == null)
            throw new EntityNotFoundException("No PersonEntity found for id=" + personEntity.getId());
        return entityManager.merge(personEntity);
    }

    public void delete(int id) {
        PersonEntity PersonEntity = entityManager.find(PersonEntity.class, id);
        if (PersonEntity != null) {
            entityManager.remove(PersonEntity);
        }
    }

    private PersonEntity findPersonByName(String personName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        ParameterExpression<String> paramName = criteriaBuilder.parameter(String.class);
        CriteriaQuery<PersonEntity> query = criteriaBuilder.createQuery(PersonEntity.class);
        query
                .select(query.from(PersonEntity.class))
                .where(criteriaBuilder.equal(query.from(PersonEntity.class).get("personName"), paramName));

        List<PersonEntity> result = entityManager.createQuery(query).setParameter(paramName, personName).getResultList();

        if (result == null || result.isEmpty())
            return null;
        else
            return result.get(0);
    }

}
