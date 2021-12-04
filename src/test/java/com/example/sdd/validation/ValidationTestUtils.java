package com.example.sdd.validation;

import com.example.sdd.dto.CountryDto;
import com.example.sdd.dto.PersonDto;

import java.util.Collections;

public final class ValidationTestUtils {

    public static final String CORRECT_DTO_MUST_HAVE_NO_SPRING_VALIDATION_ERRORS = "Корректный DTO должен не имить ошибок Spring-валидации";
    public static final String CORRECT_DTO_MUST_HAVE_NO_JAVA_VALIDATION_ERRORS = "Корректный DTO должен не имить ошибок Java-валидации";

    public static final String ERROR_CORRECT_DTO_HAS_VALIDATION_ERRORS = "ОШИБКА: Корректный DTO имеет ошибки валидации";
    public static final String ERROR_TEST_DTO_MUST_HAVE_ONE_VALIDATION_ERROR = "ОШИБКА: Тестовый DTO должен иметь ровно одну ошибку валидации";
    public static final String ERROR_VALIDATION_ERROR_MESSAGE_DOES_NOT_MATCH = "ОШИБКА: Неверное сообщение об ошибке валидации";
    public static final String ERROR_VALIDATION_ERROR_PROPERTY_DOES_NOT_MATCH = "ОШИБКА: Неверное свойство";

    public static CountryDto getCorrectCountryDto() {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(1);
        countryDto.setCountryName("Россия");

        countryDto.setPersons(Collections.singletonList(getCorrectPersonDto()));
        return countryDto;
    }

    public static PersonDto getCorrectPersonDto() {
        PersonDto personDto = new PersonDto();
        personDto.setId(1);
        personDto.setPersonName("Иванов");

        return personDto;
    }


}
