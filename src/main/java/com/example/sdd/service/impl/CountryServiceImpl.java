package com.example.sdd.service.impl;

import com.example.sdd.dao.CountryDao;
import com.example.sdd.entity.CountryEntity;
import com.example.sdd.service.CountryService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.example.sdd.validation.CustomValidationUtils.allNotEmpty;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public List<CountryEntity> getAllCountries() {
        return countryDao.findAllCountries();
    }

    public CountryEntity getCountryById(int id) {
        if (!allNotEmpty(id, countryDao.findById(id)))
            throw new EntityNotFoundException("No country found for id=" + id);

        return countryDao.findById(id);
    }

    public List<CountryEntity> getCountryByName(String name) {
        return countryDao.findByName(name);
    }

    public CountryEntity createCountry(CountryEntity countryEntity) {
        return countryDao.create(countryEntity);
    }

    public CountryEntity updateCountry(CountryEntity countryEntity) {
        if (!allNotEmpty(countryEntity, countryEntity.getId(), countryDao.findById(countryEntity.getId())))
            throw new EntityNotFoundException("No countryEntity found for id=" + countryEntity.getId());

        return countryDao.update(countryEntity);
    }

    public void deleteCountry(int id) {
        countryDao.delete(id);
    }

}
