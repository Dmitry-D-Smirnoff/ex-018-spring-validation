package com.example.sdd.service;

import com.example.sdd.entity.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountries();

    Country getCountryById(int id);

    Country createCountry(Country country);

    Country updateCountry(Country country);

    void deleteCountry(int id);

}
