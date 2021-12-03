package com.example.sdd.entity.validation;

import com.example.sdd.entity.Country;
import com.example.sdd.entity.Person;
import com.example.sdd.service.CountryService;
import com.example.sdd.validation.ErrorDetails;
import com.example.sdd.validation.ValidatedAction;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.sdd.validation.ErrorCode.ERROR_CODE_PERSON_NAME_DUPLICATION;
import static com.example.sdd.validation.ValidatedAction.ACTION_COUNTRY_CREATE;
import static com.example.sdd.validation.ValidatedAction.ACTION_COUNTRY_UPDATE;
import static com.example.sdd.validation.ValidatedAction.ACTION_PERSON_CREATE;
import static com.example.sdd.validation.ValidatedAction.ACTION_PERSON_UPDATE;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_COUNTRY_MUST_HAVE_UNIQUE_COUNTRY_NAME;

@Service
public class CountryValidator implements EntityValidator<Country> {

    private final CountryService countryService;

    private final PersonValidator personValidator;

    public CountryValidator(CountryService countryService, PersonValidator personValidator) {
        this.countryService = countryService;
        this.personValidator = personValidator;
    }

    @Override
    public List<ErrorDetails> collectValidationErrors(Country country, ValidatedAction operation, Class type) {
        List<ErrorDetails> errors = new ArrayList<>();

        for (Person person : country.getPersons()) {
            errors.addAll(personValidator.collectValidationErrors(
                    person,
                    ACTION_COUNTRY_UPDATE.equals(operation) ? ACTION_PERSON_UPDATE : ACTION_PERSON_CREATE,
                    Person.class
            ));
        }

        List<Country> duplicates = countryService.getCountryByName(country.getCountryName());
        if (!CollectionUtils.isEmpty(duplicates) && (ACTION_COUNTRY_CREATE.equals(operation)
                || duplicates.size() > 1 || !Objects.equals(duplicates.get(0).getId(), country.getId()))) {
            errors.add(new ErrorDetails(
                    ERROR_CODE_PERSON_NAME_DUPLICATION,
                    VALID_COUNTRY_MUST_HAVE_UNIQUE_COUNTRY_NAME,
                    "countryName"
            ));
        }

        return errors;
    }

}
