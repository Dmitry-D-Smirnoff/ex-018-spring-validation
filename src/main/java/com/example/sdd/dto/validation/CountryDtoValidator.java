package com.example.sdd.dto.validation;

import com.example.sdd.dto.CityDto;
import com.example.sdd.dto.CountryDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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

        if (Objects.isNull(countryDto.getCountryName())) {
            errors.rejectValue("countryName", "Наименование страны должно быть заполнено");
        }

        if (Objects.isNull(countryDto.getCities())) {
            errors.rejectValue("cities", "Перечень городов должен включать хотя бы один город");
        }

        if (!countryDto.getCities().stream().map(CityDto::getCityName).allMatch(StringUtils::hasLength)) {
            errors.rejectValue("cities", "Города создаваемой страны не могут иметь пустое наименование");
        }

    }
}
