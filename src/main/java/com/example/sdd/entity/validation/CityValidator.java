package com.example.sdd.entity.validation;

import com.example.sdd.entity.City;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CityValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return City.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object obj, @NotNull Errors errors) {
        City city = (City) obj;

    }

}
