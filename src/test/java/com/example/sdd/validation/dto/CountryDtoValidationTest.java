package com.example.sdd.validation.dto;

import com.example.sdd.dto.CountryDto;
import com.example.sdd.dto.validation.CountryDtoValidator;
import com.example.sdd.validation.ValidationTestUtils;
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
import java.util.Set;

import static com.example.sdd.validation.ValidationErrorMessages.VALID_COUNTRY_DTO_NAME_MUST_BE_FROM_3_TO_10_CHARS;
import static com.example.sdd.validation.ValidationTestUtils.CORRECT_DTO_MUST_HAVE_NO_JAVA_VALIDATION_ERRORS;
import static com.example.sdd.validation.ValidationTestUtils.CORRECT_DTO_MUST_HAVE_NO_SPRING_VALIDATION_ERRORS;
import static com.example.sdd.validation.ValidationTestUtils.ERROR_CORRECT_DTO_HAS_VALIDATION_ERRORS;
import static com.example.sdd.validation.ValidationTestUtils.ERROR_TEST_DTO_MUST_HAVE_ONE_VALIDATION_ERROR;
import static com.example.sdd.validation.ValidationTestUtils.ERROR_VALIDATION_ERROR_MESSAGE_DOES_NOT_MATCH;
import static com.example.sdd.validation.ValidationTestUtils.ERROR_VALIDATION_ERROR_PROPERTY_DOES_NOT_MATCH;
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
    @DisplayName(CORRECT_DTO_MUST_HAVE_NO_SPRING_VALIDATION_ERRORS)
    public void springValidationShouldHaveNoConstraintViolationsOnCorrectCountryDto() {
        final DataBinder dataBinder = new DataBinder(ValidationTestUtils.getCorrectCountryDto());
        dataBinder.addValidators(countryDtoValidator);
        dataBinder.validate();

        assertFalse(dataBinder.getBindingResult().hasErrors(),
                ERROR_CORRECT_DTO_HAS_VALIDATION_ERRORS);
    }

    @Test
    @DisplayName(CORRECT_DTO_MUST_HAVE_NO_JAVA_VALIDATION_ERRORS)
    public void javaValidationShouldHaveNoConstraintViolationsOnCorrectCountryDto() {
        CountryDto countryDto = ValidationTestUtils.getCorrectCountryDto();

        Set<ConstraintViolation<CountryDto>> violations = validator.validate(countryDto);
        assertTrue(violations.isEmpty(), ERROR_CORRECT_DTO_HAS_VALIDATION_ERRORS);
    }

    @Test
    @DisplayName(VALID_COUNTRY_DTO_NAME_MUST_BE_FROM_3_TO_10_CHARS)
    public void javaValidationShouldDetectInvalidCountryDtoDate() {
        CountryDto countryDto = ValidationTestUtils.getCorrectCountryDto();
        countryDto.setCountryName("ЕС");

        Set<ConstraintViolation<CountryDto>> violations = validator.validate(countryDto);
        assertEquals(1, violations.size(), ERROR_TEST_DTO_MUST_HAVE_ONE_VALIDATION_ERROR);

        ConstraintViolation<CountryDto> violation = violations.iterator().next();
        assertEquals(VALID_COUNTRY_DTO_NAME_MUST_BE_FROM_3_TO_10_CHARS, 
                violation.getMessage(), ERROR_VALIDATION_ERROR_MESSAGE_DOES_NOT_MATCH);
        assertEquals("countryName", violation.getPropertyPath().toString(), ERROR_VALIDATION_ERROR_PROPERTY_DOES_NOT_MATCH);
    }

    @Test
    @DisplayName("Наименование страны должно быть русскоязычным и начинаться с заглавной буквы")
    public void springValidationShouldDetectCountryHasCountryNameInvalid() {
        final CountryDto countryDto = ValidationTestUtils.getCorrectCountryDto();
        countryDto.setCountryName("австрия");

        final DataBinder dataBinder = new DataBinder(countryDto);
        dataBinder.addValidators(countryDtoValidator);
        dataBinder.validate();

        assertTrue(dataBinder.getBindingResult().hasErrors(),
                ERROR_TEST_DTO_MUST_HAVE_ONE_VALIDATION_ERROR);

        assertTrue(dataBinder
                        .getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(ObjectError::getCode)
                        .anyMatch("Наименование страны должно быть русскоязычным и начинаться с заглавной буквы"::equals),
                ERROR_VALIDATION_ERROR_MESSAGE_DOES_NOT_MATCH
        );
    }

}


