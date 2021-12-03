package com.example.sdd.validation;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(onlyExplicitlyIncluded = true)
public enum ErrorCode {
    ERROR_CODE_UNRECOGNIZED_ACTION("Указано недопустимое действие над объектом"),

    ERROR_CODE_ENTITY_NOT_FOUND("Запрошенный объект не найден в базе данных"),
    ERROR_CODE_CONSTRAINT_VIOLATION_EXCEPTION("ConstraintViolationException"),
    ERROR_CODE_SPRING_VALIDATION_FIELD_ERROR("SpringValidationFieldError"),
    ERROR_CODE_PERSON_NAME_DUPLICATION("Нарушение уникальности имени Гражданина"),
    ERROR_CODE_COUNTRY_NAME_DUPLICATION("Нарушение уникальности имени Страны");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
