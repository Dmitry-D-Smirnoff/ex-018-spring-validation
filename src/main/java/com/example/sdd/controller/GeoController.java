package com.example.sdd.controller;

import com.example.sdd.dto.CountryDto;
import com.example.sdd.dto.PersonDto;
import com.example.sdd.dto.validation.CountryDtoValidator;
import com.example.sdd.dto.validation.PersonDtoValidator;
import com.example.sdd.dto.validation.group.CountryCreate;
import com.example.sdd.dto.validation.group.CountryUpdate;
import com.example.sdd.dto.validation.group.PersonCreate;
import com.example.sdd.dto.validation.group.PersonUpdate;
import com.example.sdd.entity.CountryEntity;
import com.example.sdd.entity.PersonEntity;
import com.example.sdd.entity.validation.CountryEntityValidator;
import com.example.sdd.entity.validation.PersonEntityValidator;
import com.example.sdd.mapper.GeoMapper;
import com.example.sdd.service.CountryService;
import com.example.sdd.service.PersonService;
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

    private final PersonDtoValidator personDtoValidator;
    private final CountryDtoValidator countryDtoValidator;

    private final PersonEntityValidator personEntityValidator;
    private final CountryEntityValidator countryEntityValidator;

    private final GeoMapper geoMapper;

    public GeoController(
            PersonService personService,
            CountryService countryService,
            GeoMapper geoMapper,
            PersonEntityValidator personEntityValidator,
            CountryEntityValidator countryEntityValidator,
            PersonDtoValidator personDtoValidator,
            CountryDtoValidator countryDtoValidator
    ) {
        this.personService = personService;
        this.countryService = countryService;
        this.geoMapper = geoMapper;
        this.personEntityValidator = personEntityValidator;
        this.countryEntityValidator = countryEntityValidator;
        this.personDtoValidator = personDtoValidator;
        this.countryDtoValidator = countryDtoValidator;
    }

    @InitBinder("countryDto")
    protected void initCountryDtoBinder(WebDataBinder binder) {
        binder.setValidator(countryDtoValidator);
    }

    @InitBinder("personDto")
    protected void initPersonDtoBinder(WebDataBinder binder) {
        binder.setValidator(personDtoValidator);
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

    // 1. @Validated(...) обязательна для полной Java-валидации в DTO-классе.
    // 1.1. Только в этом случае учитывается проверка по JSR-303-группам валидации.
    @Validated(CountryCreate.class)
    @PostMapping("/countries")
    @ResponseStatus(HttpStatus.CREATED)
    public CountryDto createCountry(
            // 2. Наличие @Valid обязательно для корректной Java-валидации в DTO-классе.
            // 3. Наличие @Validated(...) нужно для запуска Spring-валидатора SmartValidator.
            // 3.1. @Validated(...) обязательно должна предшествовать @Valid
            // Иначе код Spring-валидации пропустит указанную JSR-303-группу. Код содержит неточности.
            // Тогда Spring отработает как Validator, не вызывая метод из SmartValidator с Object... hints.
            @RequestBody @Validated(CountryCreate.class) @Valid CountryDto countryDto
    ) {
        CountryEntity countryEntity = geoMapper.convertToEntity(countryDto);
        countryEntityValidator.validate(countryEntity, CountryCreate.class, CountryEntity.class);
        return geoMapper.convertToDto(countryService.createCountry(countryEntity));
    }
/*
Порядок аннотаций из п.3.1. важен из-за грубо написанного кода ниже в классе
org.springframework.web.method.annotation.ModelAttributeMethodProcessor.
Т.е. первая попавшаяся аннотация валидации прервет обработку.
И если это - универсальная @Valid из Java-валидации, то SmartValidator не будет вызван.

    protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
        for (Annotation ann : parameter.getParameterAnnotations()) {
            Object[] validationHints = ValidationAnnotationUtils.determineValidationHints(ann);
            if (validationHints != null) {
                binder.validate(validationHints);
                break;
            }
        }
    }

    @Nullable
    public static Object[] determineValidationHints(Annotation ann) {
        Class<? extends Annotation> annotationType = ann.annotationType();
        String annotationName = annotationType.getName();
        if ("javax.validation.Valid".equals(annotationName)) {
            return EMPTY_OBJECT_ARRAY;
        }
        Validated validatedAnn = AnnotationUtils.getAnnotation(ann, Validated.class);
        if (validatedAnn != null) {
            Object hints = validatedAnn.value();
            return convertValidationHints(hints);
        }
        if (annotationType.getSimpleName().startsWith("Valid")) {
            Object hints = AnnotationUtils.getValue(ann);
            return convertValidationHints(hints);
        }
        return null;
    }
*/

    @Validated(CountryUpdate.class)
    @PutMapping("/countries")
    @ResponseStatus(HttpStatus.OK)
    public CountryDto updateCountry(@RequestBody @Validated(CountryUpdate.class) @Valid CountryDto countryDto) {
        CountryEntity countryEntity = geoMapper.convertToEntity(countryDto);
        countryEntityValidator.validate(countryEntity, CountryUpdate.class, CountryEntity.class);
        return geoMapper.convertToDto(countryService.updateCountry(countryEntity));
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

    @Validated(PersonCreate.class)
    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto createPerson(@RequestBody @Validated(PersonCreate.class) @Valid PersonDto personDto) {
        PersonEntity personEntity = geoMapper.convertToEntity(personDto);
        personEntityValidator.validate(personEntity, PersonCreate.class, PersonEntity.class);
        return geoMapper.convertToDto(personService.createPerson(personEntity));
    }

    @Validated(PersonUpdate.class)
    @PutMapping("/persons")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto updatePerson(@RequestBody @Validated(PersonUpdate.class) @Valid PersonDto personDto) {
        PersonEntity personEntity = geoMapper.convertToEntity(personDto);
        personEntityValidator.validate(personEntity, PersonUpdate.class, PersonEntity.class);
        return geoMapper.convertToDto(personService.updatePerson(personEntity));
    }

    @DeleteMapping("/persons/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("personId") @Min(2) int id) {
        personService.deletePerson(id);
    }

}