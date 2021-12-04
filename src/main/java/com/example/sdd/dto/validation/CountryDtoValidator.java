package com.example.sdd.dto.validation;

import com.example.sdd.dto.CountryDto;
import com.example.sdd.dto.PersonDto;
import com.example.sdd.dto.validation.group.UndefinedGroup;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.ValidationUtils;

import javax.validation.constraints.NotNull;

import static com.example.sdd.validation.ExampleValidationUtils.startsWithCapitalRussianLetter;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_COUNTRY_DTO_NAME_MUST_BE_IN_RUSSIAN_WITH_FIRST_CAPITAL_LETTER;

@Service
public class CountryDtoValidator implements SmartValidator {

    private final PersonDtoValidator personDtoValidator;

    public CountryDtoValidator(PersonDtoValidator personDtoValidator) {
        this.personDtoValidator = personDtoValidator;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return CountryDto.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        validate(target, errors, UndefinedGroup.class);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors, Object... hints) {
        CountryDto countryDto = (CountryDto) target;

        if(!CollectionUtils.isEmpty(countryDto.getPersons()))
            for(PersonDto personDto : countryDto.getPersons()){
                ValidationUtils.invokeValidator(personDtoValidator, personDto, errors, hints);
            }

        if (StringUtils.hasText(countryDto.getCountryName()) && !startsWithCapitalRussianLetter(countryDto.getCountryName())) {
            errors.rejectValue("countryName", VALID_COUNTRY_DTO_NAME_MUST_BE_IN_RUSSIAN_WITH_FIRST_CAPITAL_LETTER);
        }

    }
}
