package com.example.sdd.dao.impl;

import com.example.sdd.dao.CityDao;
import com.example.sdd.entity.City;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class CityDaoImpl implements CityDao {

    private final Log log = LogFactory.getLog(getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public List<City> findAllCountries() {
        return entityManager.createNamedQuery("City.findAll", City.class).getResultList();
    }

    public List<City> findAllCities() {
        return entityManager.createNamedQuery("City.findAll", City.class).getResultList();
    }

    public City findById(int id) {
        return entityManager.find(City.class, id);
    }

    public City create(City City) {
        entityManager.persist(City);
        return City;
    }

    public City update(int id, City city) {
        if (entityManager.find(City.class, id) == null)
            throw new EntityNotFoundException("No City found for id="+id);
        city.setId(id);
        return entityManager.merge(city);
    }

    public void delete(int id) {
        City City = entityManager.find(City.class, id);
        if (City != null) {
            entityManager.remove(City);
        }
    }

    private City findCityByName(String cityName){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        ParameterExpression<String> paramName = criteriaBuilder.parameter(String.class);
        CriteriaQuery<City> query = criteriaBuilder.createQuery(City.class);
        query
                .select(query.from(City.class))
                .where(criteriaBuilder.equal(query.from(City.class).get("cityName"), paramName));

        List<City> result = entityManager.createQuery(query).setParameter(paramName, cityName).getResultList();

        if(result==null || result.isEmpty())
            return null;
        else
            return result.get(0);
    }

}
