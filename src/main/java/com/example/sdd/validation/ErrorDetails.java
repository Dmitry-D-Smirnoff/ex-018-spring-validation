package com.example.sdd.validation;

import lombok.Getter;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;

import static com.example.sdd.validation.ErrorCode.ERROR_CODE_SPRING_VALIDATION_FIELD_ERROR;

@Getter
public class ErrorDetails {
    private final String message;
    private String field;
    private final ErrorCode code;

    public ErrorDetails(ConstraintViolation<?> violation){
        this(
                ErrorCode.ERROR_CODE_CONSTRAINT_VIOLATION_EXCEPTION,
                violation.getMessage(),
                violation.getPropertyPath().toString()
        );
    }

    public ErrorDetails(FieldError error){
        this(
                ERROR_CODE_SPRING_VALIDATION_FIELD_ERROR,
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
