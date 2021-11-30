package com.example.sdd.service;

import com.example.sdd.entity.Country;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@Service
public interface CountryService {

    List<Country> getAllCountries();

    Country getCountryById(int id);

    List<Country> getCountryByName(String name);

    Country createCountry(@Valid Country country);

    Country updateCountry(@Valid Country country);

    void deleteCountry(int id);

}
