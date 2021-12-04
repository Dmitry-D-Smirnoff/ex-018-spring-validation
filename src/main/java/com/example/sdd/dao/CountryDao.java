package com.example.sdd.dao;

import com.example.sdd.entity.CountryEntity;

import java.util.List;

public interface CountryDao {

    List<CountryEntity> findAllCountries();

    CountryEntity findById(int id);

    List<CountryEntity> findByName(String name);

    CountryEntity create(CountryEntity countryEntity);

    CountryEntity update(CountryEntity countryEntity);

    void delete(int id);

}
