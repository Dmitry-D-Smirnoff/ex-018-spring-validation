package com.example.sdd.service;

import com.example.sdd.entity.City;
import com.example.sdd.entity.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountries();

    List<City> getAllCities();

    Country getCountryById(int id);

    Country createCountry(Country country);

    Country updateCountry(int id, Country country);

    void deleteCountry(int id);

}
