package com.example.sdd.controller;

import com.example.sdd.entity.City;
import com.example.sdd.entity.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public interface GeoController {

    @GetMapping("/countries")
    List<Country> getAllCountries();

    @GetMapping("/cities")
    List<City> getAllCities();

    @GetMapping("/countries/{countryId}")
    Country getCountryById(@PathVariable("countryId") int id);

    @PostMapping("/countries")
    @ResponseStatus(HttpStatus.CREATED)
    Country createCountry(@RequestBody Country country);

    @PutMapping("/countries/{countryId}")
    Country updateCountry(@PathVariable("countryId") int id, @RequestBody Country country);

    @DeleteMapping("/countries/{countryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCountry(@PathVariable("countryId") int id);
}