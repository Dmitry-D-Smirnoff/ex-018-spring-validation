package com.example.sdd.dto.validation;

import com.example.sdd.dto.CountryDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;

import static com.example.sdd.validation.ValidationErrorMessages.VALID_COUNTRY_DTO_NAME_MUST_BE_IN_RUSSIAN_WITH_FIRST_CAPITAL_LETTER;
import static com.example.sdd.validation.ExampleValidationUtils.startsWithCapitalRussianLetter;

@Service
public class CountryDtoValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return CountryDto.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object obj, @NotNull Errors errors) {
        CountryDto countryDto = (CountryDto) obj;

        if (StringUtils.hasText(countryDto.getCountryName()) && !startsWithCapitalRussianLetter(countryDto.getCountryName())) {
            errors.rejectValue("countryName", VALID_COUNTRY_DTO_NAME_MUST_BE_IN_RUSSIAN_WITH_FIRST_CAPITAL_LETTER);
        }

    }
}
