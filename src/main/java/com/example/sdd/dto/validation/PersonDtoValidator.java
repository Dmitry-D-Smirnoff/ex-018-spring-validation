package com.example.sdd.dto.validation;

import com.example.sdd.dto.PersonDto;
import com.example.sdd.dto.validation.group.CountryCreate;
import com.example.sdd.dto.validation.group.CountryUpdate;
import com.example.sdd.dto.validation.group.UndefinedGroup;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;

import javax.validation.constraints.NotNull;

import static com.example.sdd.validation.CustomValidationUtils.startsWithCapitalRussianLetter;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_PERSON_DTO_NAME_MUST_BE_IN_RUSSIAN_WITH_FIRST_CAPITAL_LETTER;

@Service
public class PersonDtoValidator implements SmartValidator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return PersonDto.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        validate(target, errors, UndefinedGroup.class);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors, Object... hints) {
        PersonDto personDto = (PersonDto) target;

        boolean fromCountryDtoValidator = hints.length == 1 && (hints[0] == CountryCreate.class || hints[0] == CountryUpdate.class);

        if (StringUtils.hasText(personDto.getPersonName()) && !startsWithCapitalRussianLetter(personDto.getPersonName())) {
            errors.rejectValue(fromCountryDtoValidator ? "persons" : "personName", VALID_PERSON_DTO_NAME_MUST_BE_IN_RUSSIAN_WITH_FIRST_CAPITAL_LETTER);
        }

    }

}