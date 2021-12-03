package com.example.sdd.entity.validation;

import com.example.sdd.dto.PersonDto;
import com.example.sdd.entity.Country;
import com.example.sdd.service.CountryService;
import com.example.sdd.service.PersonService;
import com.example.sdd.validation.ValidatedActionException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static com.example.sdd.validation.ValidatedAction.ACTION_COUNTRY_CREATE;
import static com.example.sdd.validation.ValidatedAction.ACTION_UNDEFINED;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_COUNTRY_MUST_HAVE_UNIQUE_COUNTRY_NAME;

@Service
public class DeprecatedCountryEntityValidator implements SmartValidator {

    private final DeprecatedPersonEntityValidator deprecatedPersonEntityValidator;
    private final CountryService countryService;
    private final PersonService personService;
    
    public DeprecatedCountryEntityValidator(DeprecatedPersonEntityValidator deprecatedPersonEntityValidator, CountryService countryService, PersonService personService) {
        this.deprecatedPersonEntityValidator = deprecatedPersonEntityValidator;
        this.countryService = countryService;
        this.personService = personService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return PersonDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, ACTION_UNDEFINED);
    }

    @Override
    public void validate(@NotNull Object target, Errors errors, Object... validationHints){
        try{
            HashSet<Class> actions = new HashSet<>(Arrays.asList(Arrays.copyOf(validationHints, validationHints.length, Class[].class)));
            Country country = (Country) target;

            actions.stream().forEach(System.out::println);

            List<Country> duplicates = countryService.getCountryByName(country.getCountryName());
            if (!CollectionUtils.isEmpty(duplicates) && (actions.contains(ACTION_COUNTRY_CREATE)
                    || duplicates.size() > 1 || !Objects.equals(duplicates.get(0).getId(), country.getId()))) {
                errors.rejectValue("countryName", VALID_COUNTRY_MUST_HAVE_UNIQUE_COUNTRY_NAME);
            }

        } catch (ClassCastException e){
            throw new ValidatedActionException(e.getMessage());
        }
    }

}
