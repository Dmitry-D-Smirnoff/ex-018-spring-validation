package com.example.sdd.service.impl;

import com.example.sdd.dao.CountryDao;
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

    public Country getCountryById(int id) {
        return countryDao.findById(id);
    }

    public Country getCountryByName(String name) {
        return countryDao.findByName(name);
    }

    public Country createCountry(Country country) {
        return countryDao.create(country);
    }

    public Country updateCountry(Country country) {
        return countryDao.update(country);
    }

    public void deleteCountry(int id) {
        countryDao.delete(id);
    }

}
