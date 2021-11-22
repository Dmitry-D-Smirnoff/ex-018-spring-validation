package com.example.sdd.service.impl;

import com.example.sdd.dao.CountryDao;
import com.example.sdd.entity.City;
import com.example.sdd.entity.Country;
import com.example.sdd.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public List<Country> getAllCountries() {
        return countryDao.findAllCountries();
    }

    public List<City> getAllCities() {
        return countryDao.findAllCities();
    }

    public Country getCountryById(int id) {
        return countryDao.findById(id);
    }

    public Country createCountry(Country country) {
        return countryDao.create(country);
    }

    public Country updateCountry(int id, Country country) {
        return countryDao.update(id, country);
    }

    public void deleteCountry(int id) {
        countryDao.delete(id);
    }

}
