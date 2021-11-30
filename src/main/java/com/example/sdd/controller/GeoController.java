package com.example.sdd.controller;

import com.example.sdd.dto.CountryDto;
import com.example.sdd.dto.PersonDto;
import com.example.sdd.dto.validation.CountryDtoValidator;
import com.example.sdd.dto.validation.PersonDtoValidator;
import com.example.sdd.dto.validation.group.CountryDtoUpdateGroup;
import com.example.sdd.dto.validation.group.PersonDtoUpdateGroup;
import com.example.sdd.entity.Country;
import com.example.sdd.entity.Person;
import com.example.sdd.entity.validation.CountryValidator;
import com.example.sdd.entity.validation.PersonValidator;
import com.example.sdd.mapper.GeoMapper;
import com.example.sdd.service.CountryService;
import com.example.sdd.service.PersonService;
import com.example.sdd.validation.ValidatedOperation;
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
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeoController {

    private final PersonService personService;
    private final CountryService countryService;

    private final PersonValidator personValidator;
    private final CountryValidator countryValidator;

    private final GeoMapper geoMapper;

    public GeoController(
            PersonService personService,
            CountryService countryService,
            GeoMapper geoMapper,
            PersonValidator personValidator,
            CountryValidator countryValidator
    ) {
        this.personService = personService;
        this.countryService = countryService;
        this.geoMapper = geoMapper;
        this.personValidator = personValidator;
        this.countryValidator = countryValidator;
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
    public CountryDto getCountryById(@PathVariable("countryId") @Min(1) int id) {
        return geoMapper.convertToDto(countryService.getCountryById(id));
    }

    @GetMapping("/countries/byName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<CountryDto> getCountryByName(@PathVariable("name") @NotBlank String name) {
        return countryService.getCountryByName(name).stream().map(geoMapper::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/countries")
    @ResponseStatus(HttpStatus.CREATED)
    public CountryDto createCountry(@RequestBody @Valid CountryDto countryDto) {
        Country country = geoMapper.convertToEntity(countryDto);
        countryValidator.validate(country, ValidatedOperation.COUNTRY_CREATE, Country.class);
        return geoMapper.convertToDto(countryService.createCountry(geoMapper.convertToEntity(countryDto)));
    }

    @PutMapping("/countries")
    @ResponseStatus(HttpStatus.OK)
    public CountryDto updateCountry(@RequestBody @Validated(CountryDtoUpdateGroup.class) CountryDto countryDto) {
        Country country = geoMapper.convertToEntity(countryDto);
        countryValidator.validate(country, ValidatedOperation.COUNTRY_UPDATE, Country.class);
        return geoMapper.convertToDto(countryService.updateCountry(geoMapper.convertToEntity(countryDto)));
    }

    @DeleteMapping("/countries/{countryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountry(@PathVariable("countryId") @Min(2) int id) {
        countryService.deleteCountry(id);
    }

    @GetMapping("/persons")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getAllPersons() {
        return personService.getAllPersons().stream().map(geoMapper::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/persons/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@PathVariable("personId") @Min(1) int id) {
        return geoMapper.convertToDto(personService.getPersonById(id));
    }

    @GetMapping("/persons/byName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPersonByName(@PathVariable("name") @NotBlank String name) {
        return personService.getPersonByName(name).stream().map(geoMapper::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto createPerson(@RequestBody @Valid PersonDto personDto) {
        Person person = geoMapper.convertToEntity(personDto);
        personValidator.validate(person, ValidatedOperation.PERSON_CREATE, Person.class);
        return geoMapper.convertToDto(personService.createPerson(geoMapper.convertToEntity(personDto)));
    }

    @PutMapping("/persons")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto updatePerson(@RequestBody @Validated(PersonDtoUpdateGroup.class) PersonDto personDto) {
        Person person = geoMapper.convertToEntity(personDto);
        personValidator.validate(person, ValidatedOperation.PERSON_UPDATE, Person.class);
        return geoMapper.convertToDto(personService.updatePerson(geoMapper.convertToEntity(personDto)));
    }

    @DeleteMapping("/persons/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("personId") @Min(2) int id) {
        personService.deletePerson(id);
    }

}