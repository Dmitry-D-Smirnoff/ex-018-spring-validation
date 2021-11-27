package com.example.sdd.service;

import com.example.sdd.entity.City;

import java.util.List;

public interface CityService {

    List<City> getAllCities();

    City getCityById(int id);

    City createCity(City city);

    City updateCity(City city);

    void deleteCity(int id);

}
