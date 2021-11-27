package com.example.sdd.controller;

import com.example.sdd.dto.CityDto;
import com.example.sdd.dto.CountryDto;
import com.example.sdd.dto.validation.CityDtoValidator;
import com.example.sdd.dto.validation.CountryDtoValidator;
import com.example.sdd.mapper.GeoMapper;
import com.example.sdd.service.CityService;
import com.example.sdd.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeoController {

    private final CityService cityService;
    private final CountryService countryService;

    private final GeoMapper geoMapper;

    public GeoController(
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

    @GetMapping("/countries")
    @ResponseStatus(HttpStatus.OK)
    public List<CountryDto> getAllCountries() {
        return countryService.getAllCountries().stream().map(geoMapper::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/countries/{countryId}")
    @ResponseStatus(HttpStatus.OK)
    public CountryDto getCountryById(@PathVariable("countryId") @Min(2) int id) {
        return geoMapper.convertToDto(countryService.getCountryById(id));
    }

    @PostMapping("/countries")
    @ResponseStatus(HttpStatus.CREATED)
    public CountryDto createCountry(@Valid @RequestBody CountryDto countryDto) {
        return geoMapper.convertToDto(countryService.createCountry(geoMapper.convertToEntity(countryDto)));
    }

    @PutMapping("/countries")
    @ResponseStatus(HttpStatus.OK)
    public CountryDto updateCountry(@RequestBody CountryDto countryDto) {
        return geoMapper.convertToDto(countryService.updateCountry(geoMapper.convertToEntity(countryDto)));
    }

    @DeleteMapping("/countries/{countryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountry(@PathVariable("countryId") int id) {
        countryService.deleteCountry(id);
    }

    @GetMapping("/cities")
    @ResponseStatus(HttpStatus.OK)
    public List<CityDto> getAllCities() {
        return cityService.getAllCities().stream().map(geoMapper::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/cities/{cityId}")
    @ResponseStatus(HttpStatus.OK)
    public CityDto getCityById(@PathVariable("cityId") @Min(2) int id) {
        return geoMapper.convertToDto(cityService.getCityById(id));
    }

    @PostMapping("/cities")
    @ResponseStatus(HttpStatus.CREATED)
    public CityDto createCity(@Valid @RequestBody CityDto cityDto) {
        return geoMapper.convertToDto(cityService.createCity(geoMapper.convertToEntity(cityDto)));
    }

    @PutMapping("/cities")
    @ResponseStatus(HttpStatus.OK)
    public CityDto updateCity(@RequestBody CityDto cityDto) {
        return geoMapper.convertToDto(cityService.updateCity(geoMapper.convertToEntity(cityDto)));
    }

    @DeleteMapping("/cities/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable("cityId") int id) {
        cityService.deleteCity(id);
    }

}