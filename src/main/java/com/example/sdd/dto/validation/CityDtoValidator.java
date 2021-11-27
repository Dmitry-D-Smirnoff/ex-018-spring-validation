package com.example.sdd.dto.validation;

import com.example.sdd.dto.CityDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Service
public class CityDtoValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return CityDto.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object obj, @NotNull Errors errors) {
        CityDto cityDto = (CityDto) obj;

        if (Objects.isNull(cityDto.getCityName())) {
            errors.rejectValue("cityName", "Наименование страны должно быть заполнено");
        }

        if (Objects.isNull(cityDto.getCountry())) {
            errors.rejectValue("country", "Для города должна быть указана страна");
        }

    }

}