package com.example.sdd.entity.validation;

import com.example.sdd.entity.Country;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class CountryValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return Country.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object obj, @NotNull Errors errors) {
        Country country = (Country) obj;

    }

}

