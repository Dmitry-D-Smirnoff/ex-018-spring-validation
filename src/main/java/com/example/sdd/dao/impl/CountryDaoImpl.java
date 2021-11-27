package com.example.sdd.dao.impl;

import com.example.sdd.dao.CountryDao;
import com.example.sdd.entity.City;
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

    public Country create(Country country) {
        entityManager.persist(country);
        return country;
    }

    public Country update(Country country) {
        //TODO: Перенести проверку в валидатор сущности?
        if (entityManager.find(Country.class, country.getId()) == null)
            throw new EntityNotFoundException("No country found for id=" + country.getId());
        for (City city : country.getCities()) {
            City existingCity = entityManager.find(City.class, city.getId());
            if (existingCity != null) {
                existingCity.setCityName(city.getCityName());
                entityManager.merge(existingCity);
            } else {
                entityManager.persist(city);
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

    private City findCityByName(String cityName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        ParameterExpression<String> paramName = criteriaBuilder.parameter(String.class);
        CriteriaQuery<City> query = criteriaBuilder.createQuery(City.class);
        query
                .select(query.from(City.class))
                .where(criteriaBuilder.equal(query.from(City.class).get("cityName"), paramName));

        List<City> result = entityManager.createQuery(query).setParameter(paramName, cityName).getResultList();
        // Equivalent to:
        // List<City> result = entityManager.createQuery("SELECT s FROM City s WHERE s.cityName = :cityName", City.class).setParameter("cityName", cityName).getResultList();

        if (result == null || result.isEmpty())
            return null;
        else
            return result.get(0);
    }

}
