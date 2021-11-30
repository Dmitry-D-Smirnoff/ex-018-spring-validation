package com.example.sdd.validation;

import com.example.sdd.dto.CountryDto;
import com.example.sdd.dto.PersonDto;
import com.example.sdd.dto.validation.CountryDtoValidator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CountryDtoValidationTest {

    @Autowired
    private CountryDtoValidator countryDtoValidator;

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @Test
    @DisplayName("Корректная страна не имеет ошибок Spring-валидации")
    public void javaValidationShouldHaveNoConstraintViolationsOnCorrectCountryDto() {
        final DataBinder dataBinder = new DataBinder(getCorrectCountryDto());
        dataBinder.addValidators(countryDtoValidator);
        dataBinder.validate();

        assertFalse(dataBinder.getBindingResult().hasErrors(),
                "ОШИБКА: Валидация корректной страны прошла НЕуспешно");
    }

    @Test
    @DisplayName("Корректная страна не имеет ошибок Java-валидации")
    public void springValidationShouldHaveNoConstraintViolationsOnCorrectCountryDto() {
        CountryDto countryDto = getCorrectCountryDto();

        Set<ConstraintViolation<CountryDto>> violations = validator.validate(countryDto);
        assertTrue(violations.isEmpty(), "ОШИБКА: Валидация корректной страны прошла НЕуспешно");
    }

    @Test
    @DisplayName("Наименование страны должно быть от 3 до 10 символов")
    public void javaValidationShouldDetectInvalidCountryDtoDate() {
        CountryDto countryDto = getCorrectCountryDto();
        countryDto.setCountryName("ЕС");

        Set<ConstraintViolation<CountryDto>> violations = validator.validate(countryDto);
        assertEquals(1, violations.size(), "ОШИБКА: Валидация должна была найти ровно 1 ошибку");

        ConstraintViolation<CountryDto> violation = violations.iterator().next();
        assertEquals("Наименование страны должно быть от 3 до 10 символов", violation.getMessage(), "ОШИБКА: Неверное сообщение");
        assertEquals("countryName", violation.getPropertyPath().toString(), "ОШИБКА: Неверное свойство");
    }

    @Test
    @DisplayName("Наименование страны должно быть русскоязычным и начинаться с заглавной буквы")
    public void springValidationShouldDetectCountryHasCountryNameInvalid() {
        final CountryDto countryDto = getCorrectCountryDto();
        countryDto.setCountryName("австрия");

        final DataBinder dataBinder = new DataBinder(countryDto);
        dataBinder.addValidators(countryDtoValidator);
        dataBinder.validate();

        assertTrue(dataBinder.getBindingResult().hasErrors(),
                "ОШИБКА: Валидация без указания страны прошла успешно");

        assertTrue(dataBinder
                        .getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(ObjectError::getCode)
                        .anyMatch("Наименование страны должно быть русскоязычным и начинаться с заглавной буквы"::equals),
                "ОШИБКА: Неверное сообщение"
        );
    }

    private CountryDto getCorrectCountryDto(){
        CountryDto countryDto = new CountryDto();
        countryDto.setCountryName("Россия");

        countryDto.setPersons(Collections.singletonList(getCorrectPersonDto()));
        return countryDto;
    }

    private PersonDto getCorrectPersonDto(){
        PersonDto personDto = new PersonDto();
        personDto.setPersonName("Иванов");

        return personDto;
    }

}


