package com.example.sdd.dao.impl;

import com.example.sdd.dao.CountryDao;
import com.example.sdd.entity.CountryEntity;
import com.example.sdd.entity.PersonEntity;
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

    public List<CountryEntity> findAllCountries() {
        return entityManager.createNamedQuery("CountryEntity.findAll", CountryEntity.class).getResultList();
    }

    public CountryEntity findById(int id) {
        return entityManager.find(CountryEntity.class, id);
    }

    public List<CountryEntity> findByName(String name) {
        return entityManager.createNamedQuery("CountryEntity.findByName", CountryEntity.class)
                .setParameter("name", name).getResultList();
    }

    public CountryEntity create(CountryEntity countryEntity) {
        entityManager.persist(countryEntity);
        return countryEntity;
    }

    public CountryEntity update(CountryEntity countryEntity) {
        for (PersonEntity personEntity : countryEntity.getPersons()) {
            PersonEntity existingPersonEntity = entityManager.find(PersonEntity.class, personEntity.getId());
            if (existingPersonEntity != null) {
                existingPersonEntity.setPersonName(personEntity.getPersonName());
                entityManager.merge(existingPersonEntity);
            } else {
                entityManager.persist(personEntity);
            }
        }
        return entityManager.merge(countryEntity);
    }

    public void delete(int id) {
        CountryEntity countryEntity = entityManager.find(CountryEntity.class, id);
        if (countryEntity != null) {
            entityManager.remove(countryEntity);
        }
    }

}
