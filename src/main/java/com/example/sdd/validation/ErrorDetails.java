package com.example.sdd.validation;

import lombok.Getter;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;

@Getter
public class ErrorDetails {
    private final String message;
    private final String field;

    public ErrorDetails(ConstraintViolation<?> violation) {
        this(violation.getMessage(), violation.getPropertyPath().toString());
    }

    public ErrorDetails(FieldError error) {
        this(error.getCode(), error.getField());
    }

    public ErrorDetails(String errorMessage, String field) {
        this.message = errorMessage;
        this.field = field;
    }

}
