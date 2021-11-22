package com.example.sdd.dao;

import com.example.sdd.entity.City;
import com.example.sdd.entity.Country;

import java.util.List;

public interface CountryDao {

    List<Country> findAllCountries();

    List<City> findAllCities();

    Country findById(int id);

    Country create(Country country);

    Country update(int id, Country country);

    void delete(int id);

}
