package com.example.sdd.service.impl;

import com.example.sdd.dao.CityDao;
import com.example.sdd.entity.City;
import com.example.sdd.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;

    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public List<City> getAllCities() {
        return cityDao.findAllCities();
    }

    public City getCityById(int id) {
        return cityDao.findById(id);
    }

    public City createCity(City city) {
        return cityDao.create(city);
    }

    public City updateCity(City city) {
        return cityDao.update(city);
    }

    public void deleteCity(int id) {
        cityDao.delete(id);
    }

}
