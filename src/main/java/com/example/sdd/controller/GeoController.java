package com.example.sdd.controller;

import com.example.sdd.dto.CityDto;
import com.example.sdd.dto.CountryDto;
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

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public interface GeoController {

    @GetMapping("/countries")
    List<CountryDto> getAllCountries();

    @GetMapping("/countries/{countryId}")
    CountryDto getCountryById(@PathVariable("countryId") @Min(2) int id);

    @PostMapping("/countries")
    @ResponseStatus(HttpStatus.CREATED)
    CountryDto createCountry(@Valid @RequestBody CountryDto countryDto);

    @PutMapping("/countries/{countryId}")
    CountryDto updateCountry(@PathVariable("countryId") int id, @RequestBody CountryDto countryDto);

    @DeleteMapping("/countries/{countryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCountry(@PathVariable("countryId") int id);

    @GetMapping("/cities")
    List<CityDto> getAllCities();

    @GetMapping("/cities/{cityId}")
    CityDto getCityById(@PathVariable("cityId") @Min(2) int id);

    @PostMapping("/cities")
    @ResponseStatus(HttpStatus.CREATED)
    CityDto createCity(@Valid @RequestBody CityDto cityDto);

    @PutMapping("/cities/{cityId}")
    CityDto updateCity(@PathVariable("cityId") int id, @RequestBody CityDto cityDto);

    @DeleteMapping("/cities/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCity(@PathVariable("cityId") int id);

}