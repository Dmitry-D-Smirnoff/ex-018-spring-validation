package com.example.sdd.validation;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(onlyExplicitlyIncluded = true)
public enum ErrorCode {
    NOT_FOUND_ENTITY("Запрошенный объект не найден в базе данных"),

    VALIDATION_CONSTRAINT_VIOLATION_EXCEPTION("ConstraintViolationException"),
    VALIDATION_DTO_SPRING_FIELD_ERROR("JavaEEFieldError"),
    VALIDATION_001_PERSON_NAME_DUPLICATION("Нарушение уникальности имени Гражданина"),
    VALIDATION_002_COUNTRY_NAME_DUPLICATION("Нарушение уникальности имени Страны");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
