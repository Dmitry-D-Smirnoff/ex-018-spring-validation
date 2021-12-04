package com.example.sdd.entity.validation;

import com.example.sdd.dto.validation.group.CountryCreate;
import com.example.sdd.dto.validation.group.CountryUpdate;
import com.example.sdd.dto.validation.group.PersonCreate;
import com.example.sdd.dto.validation.group.PersonUpdate;
import com.example.sdd.entity.CountryEntity;
import com.example.sdd.entity.PersonEntity;
import com.example.sdd.service.CountryService;
import com.example.sdd.validation.ErrorDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.sdd.validation.ErrorCode.ERROR_CODE_PERSON_NAME_DUPLICATION;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_COUNTRY_MUST_HAVE_UNIQUE_COUNTRY_NAME;

@Service
public class CountryEntityValidator implements EntityValidator<CountryEntity> {

    private final CountryService countryService;

    private final PersonEntityValidator personEntityValidator;

    public CountryEntityValidator(CountryService countryService, PersonEntityValidator personEntityValidator) {
        this.countryService = countryService;
        this.personEntityValidator = personEntityValidator;
    }

    @Override
    public List<ErrorDetails> collectValidationErrors(CountryEntity target, Class<?> hint, Class<CountryEntity> targetType) {
        List<ErrorDetails> errors = new ArrayList<>();

        for (PersonEntity personEntity : target.getPersons()) {
            errors.addAll(personEntityValidator.collectValidationErrors(
                    personEntity,
                    CountryUpdate.class.equals(hint) ? PersonUpdate.class : PersonCreate.class,
                    PersonEntity.class
            ));
        }

        List<CountryEntity> duplicates = countryService.getCountryByName(target.getCountryName());
        if (!CollectionUtils.isEmpty(duplicates) && (CountryCreate.class.equals(hint)
                || duplicates.size() > 1 || !Objects.equals(duplicates.get(0).getId(), target.getId()))) {
            errors.add(new ErrorDetails(
                    ERROR_CODE_PERSON_NAME_DUPLICATION,
                    VALID_COUNTRY_MUST_HAVE_UNIQUE_COUNTRY_NAME,
                    "countryName"
            ));
        }

        return errors;
    }

}
