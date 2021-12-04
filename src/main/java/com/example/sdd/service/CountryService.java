package com.example.sdd.service;

import com.example.sdd.entity.CountryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {

    List<CountryEntity> getAllCountries();

    CountryEntity getCountryById(int id);

    List<CountryEntity> getCountryByName(String name);

    CountryEntity createCountry(CountryEntity countryEntity);

    CountryEntity updateCountry(CountryEntity countryEntity);

    void deleteCountry(int id);

}
