package com.example.sdd.dto.validation;

import com.example.sdd.dto.PersonDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;

import static com.example.sdd.validation.ValidationErrorMessages.VALID_PERSON_DTO_NAME_MUST_BE_IN_RUSSIAN_WITH_FIRST_CAPITAL_LETTER;
import static com.example.sdd.validation.ExampleValidationUtils.containsOnlyRussianLettersAndSpaces;

@Service
public class PersonDtoValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return PersonDto.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object obj, @NotNull Errors errors) {
        PersonDto personDto = (PersonDto) obj;

        if (StringUtils.hasText(personDto.getPersonName()) && !containsOnlyRussianLettersAndSpaces(personDto.getPersonName())) {
            errors.rejectValue("personName", VALID_PERSON_DTO_NAME_MUST_BE_IN_RUSSIAN_WITH_FIRST_CAPITAL_LETTER);
        }

    }

}