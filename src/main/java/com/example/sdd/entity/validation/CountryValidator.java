package com.example.sdd.entity.validation;

import com.example.sdd.entity.Country;
import com.example.sdd.entity.Person;
import com.example.sdd.service.CountryService;
import com.example.sdd.validation.ErrorCode;
import com.example.sdd.validation.ErrorDetails;
import com.example.sdd.validation.ValidatedOperation;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CountryValidator implements EntityValidator<Country> {

    private final CountryService countryService;

    private final PersonValidator personValidator;

    public CountryValidator(CountryService countryService, PersonValidator personValidator) {
        this.countryService = countryService;
        this.personValidator = personValidator;
    }

    @Override
    public List<ErrorDetails> collectValidationErrors(Country country, ValidatedOperation operation, Class type) {
        List<ErrorDetails> errors = new ArrayList<>();

        for(Person person : country.getPersons()){
            errors.addAll(personValidator.collectValidationErrors(
                    person,
                    ValidatedOperation.COUNTRY_UPDATE.equals(operation)
                            ? ValidatedOperation.PERSON_UPDATE
                            : ValidatedOperation.PERSON_CREATE,
                    Person.class
            ));
        }

        List<Country> duplicates = countryService.getCountryByName(country.getCountryName());
        if (!CollectionUtils.isEmpty(duplicates) && ( ValidatedOperation.COUNTRY_CREATE.equals(operation)
                || duplicates.size() > 1 || !Objects.equals(duplicates.get(0).getId(), country.getId()))) {
            errors.add(new ErrorDetails(
                    ErrorCode.VALIDATION_001_PERSON_NAME_DUPLICATION,
                    "Такое имя Страны уже используется",
                    "countryName"
            ));
        }

        return errors;
    }

}
