package com.example.sdd.controller.impl;

import com.example.sdd.controller.GeoController;
import com.example.sdd.entity.City;
import com.example.sdd.entity.Country;
import com.example.sdd.service.CountryService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeoControllerImpl implements GeoController {

    private final CountryService countryService;

    public GeoControllerImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    public List<City> getAllCities() {
        return countryService.getAllCities();
    }

    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    public Country getCountryById(int id) {
        return countryService.getCountryById(id);
    }

    public Country createCountry(Country country) {
        return countryService.createCountry(country);
    }

    public Country updateCountry(int id, Country country) {
        return countryService.updateCountry(id, country);
    }

    public void deleteCountry(int id) {
        countryService.deleteCountry(id);
    }
}