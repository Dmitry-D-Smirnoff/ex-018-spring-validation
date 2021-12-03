package com.example.sdd.service;

import com.example.sdd.entity.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {

    List<Country> getAllCountries();

    Country getCountryById(int id);

    List<Country> getCountryByName(String name);

    Country createCountry(Country country);

    Country updateCountry(Country country);

    void deleteCountry(int id);

}
