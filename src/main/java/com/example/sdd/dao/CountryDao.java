package com.example.sdd.dao;

import com.example.sdd.entity.Country;

import java.util.List;

public interface CountryDao {

    List<Country> findAllCountries();

    Country findById(int id);

    Country create(Country country);

    Country update(Country country);

    void delete(int id);

}
