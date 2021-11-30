package com.example.sdd.dto.validation;

import com.example.sdd.dto.PersonDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Service
public class PersonDtoValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return PersonDto.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object obj, @NotNull Errors errors) {
        PersonDto personDto = (PersonDto) obj;

        if (Objects.isNull(personDto.getPersonName())) {
            errors.rejectValue("personName", "Наименование страны должно быть заполнено");
        }

        if (Objects.isNull(personDto.getCountry())) {
            errors.rejectValue("country", "Для города должна быть указана страна");
        }

    }

}