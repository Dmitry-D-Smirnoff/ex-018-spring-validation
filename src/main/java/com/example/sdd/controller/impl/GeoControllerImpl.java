package com.example.sdd.controller.impl;

import com.example.sdd.controller.GeoController;
import com.example.sdd.dto.CityDto;
import com.example.sdd.dto.CountryDto;
import com.example.sdd.dto.validation.CityDtoValidator;
import com.example.sdd.dto.validation.CountryDtoValidator;
import com.example.sdd.mapper.GeoMapper;
import com.example.sdd.service.CityService;
import com.example.sdd.service.CountryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
public class GeoControllerImpl implements GeoController {

    private final CityService cityService;
    private final CountryService countryService;

    private final GeoMapper geoMapper;

    public GeoControllerImpl(
            CityService cityService,
            CountryService countryService,
            GeoMapper geoMapper
    ) {
        this.cityService = cityService;
        this.countryService = countryService;
        this.geoMapper = geoMapper;
    }

    @InitBinder("countryDto")
    protected void initCountryDtoBinder(WebDataBinder binder) {
        binder.setValidator(new CountryDtoValidator());
    }

    @InitBinder("cityDto")
    protected void initCityDtoBinder(WebDataBinder binder) {
        binder.setValidator(new CityDtoValidator());
    }

    @Override
    public List<CountryDto> getAllCountries() {
        return countryService.getAllCountries().stream().map(geoMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CountryDto getCountryById(int id) {
        return geoMapper.convertToDto(countryService.getCountryById(id));
    }

    @Override
    public CountryDto createCountry(@Valid @RequestBody CountryDto countryDto) {
        return geoMapper.convertToDto(countryService.createCountry(geoMapper.convertToEntity(countryDto)));
    }

    @Override
    public CountryDto updateCountry(int id, CountryDto countryDto) {
        return geoMapper.convertToDto(countryService.updateCountry(id, geoMapper.convertToEntity(countryDto)));
    }

    @Override
    public void deleteCountry(int id) {
        countryService.deleteCountry(id);
    }

    @Override
    public List<CityDto> getAllCities() {
        return cityService.getAllCities().stream().map(geoMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CityDto getCityById(int id) {
        return geoMapper.convertToDto(cityService.getCityById(id));
    }

    @Override
    public CityDto createCity(@Valid @RequestBody CityDto cityDto) {
        return geoMapper.convertToDto(cityService.createCity(geoMapper.convertToEntity(cityDto)));
    }

    @Override
    public CityDto updateCity(int id, CityDto cityDto) {
        return geoMapper.convertToDto(cityService.updateCity(id, geoMapper.convertToEntity(cityDto)));
    }

    @Override
    public void deleteCity(int id) {
        cityService.deleteCity(id);
    }

}