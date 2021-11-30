package com.example.sdd.validation;

import lombok.Getter;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;

@Getter
public class ErrorDetails {
    private final String message;
    private String field;
    private final ErrorCode code;

    public ErrorDetails(ConstraintViolation<?> violation){
        this(
                ErrorCode.VALIDATION_CONSTRAINT_VIOLATION_EXCEPTION,
                violation.getMessage(),
                violation.getPropertyPath().toString()
        );
    }

    public ErrorDetails(FieldError error){
        this(
                ErrorCode.VALIDATION_DTO_SPRING_FIELD_ERROR,
                error.getCode(),
                error.getField()
        );
    }

    public ErrorDetails(ErrorCode errorCode) {
        this(errorCode, errorCode.getMessage());
    }

    public ErrorDetails(ErrorCode errorCode, String errorMessage) {
        this.code = errorCode;
        this.message = errorMessage;
    }

    public ErrorDetails(ErrorCode errorCode, String errorMessage, String field) {
        this.code = errorCode;
        this.message = errorMessage;
        this.field = field;
    }

}
