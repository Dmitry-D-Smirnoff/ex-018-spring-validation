package com.example.sdd.dao;

import com.example.sdd.entity.City;

import java.util.List;

public interface CityDao {

    List<City> findAllCities();

    City findById(int id);

    City create(City city);

    City update(City city);

    void delete(int id);

}
