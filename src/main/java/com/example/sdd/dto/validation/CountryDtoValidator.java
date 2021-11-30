package com.example.sdd.dto.validation;

import com.example.sdd.dto.CountryDto;
import com.example.sdd.validation.ValidationUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Service
public class CountryDtoValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return CountryDto.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object obj, @NotNull Errors errors) {
        CountryDto countryDto = (CountryDto) obj;

        if (Objects.isNull(countryDto.getPersons())) {
            errors.rejectValue("persons", "Перечень граждан должен включать хотя бы одного человека");
        }

        if (StringUtils.hasText(countryDto.getCountryName()) && !ValidationUtils.startsWithCapitalRussianLetter(countryDto.getCountryName())) {
            errors.rejectValue("countryName", "Наименование страны должно быть русскоязычным и начинаться с заглавной буквы");
        }

    }
}
