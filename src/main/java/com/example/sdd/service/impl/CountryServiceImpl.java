package com.example.sdd.service.impl;

import com.example.sdd.dao.CountryDao;
import com.example.sdd.entity.Country;
import com.example.sdd.service.CountryService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.example.sdd.validation.ExampleValidationUtils.allNotEmpty;

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
        if (!allNotEmpty(id, countryDao.findById(id)))
            throw new EntityNotFoundException("No country found for id=" + id);

        return countryDao.findById(id);
    }

    public List<Country> getCountryByName(String name) {
        return countryDao.findByName(name);
    }

    public Country createCountry(Country country) {
        return countryDao.create(country);
    }

    public Country updateCountry(Country country) {
        if (!allNotEmpty(country, country.getId(), countryDao.findById(country.getId())))
            throw new EntityNotFoundException("No country found for id=" + country.getId());

        return countryDao.update(country);
    }

    public void deleteCountry(int id) {
        countryDao.delete(id);
    }

}
