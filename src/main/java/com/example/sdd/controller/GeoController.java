package com.example.sdd.controller;

import com.example.sdd.dto.PersonDto;
import com.example.sdd.dto.CountryDto;
import com.example.sdd.dto.validation.PersonDtoValidator;
import com.example.sdd.dto.validation.CountryDtoValidator;
import com.example.sdd.mapper.GeoMapper;
import com.example.sdd.service.PersonService;
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

    private final PersonService personService;
    private final CountryService countryService;

    private final GeoMapper geoMapper;

    public GeoController(
            PersonService personService,
            CountryService countryService,
            GeoMapper geoMapper
    ) {
        this.personService = personService;
        this.countryService = countryService;
        this.geoMapper = geoMapper;
    }

    @InitBinder("countryDto")
    protected void initCountryDtoBinder(WebDataBinder binder) {
        binder.setValidator(new CountryDtoValidator());
    }

    @InitBinder("personDto")
    protected void initPersonDtoBinder(WebDataBinder binder) {
        binder.setValidator(new PersonDtoValidator());
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

    @GetMapping("/countries/byName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CountryDto getCountryById(@PathVariable("name") String name) {
        return geoMapper.convertToDto(countryService.getCountryByName(name));
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
    public List<PersonDto> getAllCities() {
        return personService.getAllCities().stream().map(geoMapper::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/cities/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@PathVariable("personId") @Min(2) int id) {
        return geoMapper.convertToDto(personService.getPersonById(id));
    }

    @GetMapping("/cities/byName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@PathVariable("name") String name) {
        return geoMapper.convertToDto(personService.getPersonByName(name));
    }

    @PostMapping("/cities")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto createPerson(@Valid @RequestBody PersonDto personDto) {
        return geoMapper.convertToDto(personService.createPerson(geoMapper.convertToEntity(personDto)));
    }

    @PutMapping("/cities")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto updatePerson(@RequestBody PersonDto personDto) {
        return geoMapper.convertToDto(personService.updatePerson(geoMapper.convertToEntity(personDto)));
    }

    @DeleteMapping("/cities/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("personId") int id) {
        personService.deletePerson(id);
    }

}