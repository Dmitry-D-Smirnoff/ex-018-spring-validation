package com.example.sdd.controller.impl;

import com.example.sdd.controller.GeoController;
import com.example.sdd.dto.CityDto;
import com.example.sdd.dto.CountryDto;
import com.example.sdd.dto.validation.CountryDtoValidator;
import com.example.sdd.mapper.GeoMapper;
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

    private final CountryService countryService;

    private final GeoMapper geoMapper;

    //private Validator countryDtoValidator;

    public GeoControllerImpl(
            CountryService countryService,
            GeoMapper geoMapper//,
            //@Qualifier("countryDtoValidator") Validator countryDtoValidator
    ) {
        this.countryService = countryService;
        this.geoMapper = geoMapper;
        //this.countryDtoValidator = countryDtoValidator;
    }

    @InitBinder("countryDto")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CountryDtoValidator());
    }

    public List<CityDto> getAllCities() {
        return countryService.getAllCities().stream().map(geoMapper::convertToDto).collect(Collectors.toList());
    }

    public List<CountryDto> getAllCountries() {
        return countryService.getAllCountries().stream().map(geoMapper::convertToDto).collect(Collectors.toList());
    }

    public CountryDto getCountryById(int id) {
        return geoMapper.convertToDto(countryService.getCountryById(id));
    }

    public CountryDto createCountry(@Valid @RequestBody CountryDto countryDto) { //}, BindingResult bindingResult) {
        //countryDtoValidator.validate(countryDto, bindingResult);
        return geoMapper.convertToDto(countryService.createCountry(geoMapper.convertToEntity(countryDto)));
    }

    public CountryDto updateCountry(int id, CountryDto countryDto) {
        return geoMapper.convertToDto(countryService.updateCountry(id, geoMapper.convertToEntity(countryDto)));
    }

    public void deleteCountry(int id) {
        countryService.deleteCountry(id);
    }

}