package com.example.sdd.validation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( // error handle for @Valid
            MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            HttpStatus status, @NotNull WebRequest request
    ) {
        List<ErrorDetails> errorDetailsList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorDetails::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(getErrorResponse(
                "MethodArgumentNotValidException",
                errorDetailsList
        ), headers, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ValidationErrorResponse handleConstraintViolationException(ConstraintViolationException e) {

        List<ErrorDetails> errorDetailsList = e.getConstraintViolations()
                .stream()
                .map(ErrorDetails::new)
                .collect(Collectors.toList());

        return getErrorResponse(
                "ConstraintViolationException",
                errorDetailsList
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ValidationErrorResponse handleValidationException(ValidationException e) {

        return getErrorResponse(
                e.getMainMessage(),
                e.getErrors()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ValidationErrorResponse handleEntityNotFoundException(EntityNotFoundException e) {

        return getErrorResponse(
                "ОШИБКА: Объект не найден",
                Collections.singletonList(new ErrorDetails(ErrorCode.NOT_FOUND_ENTITY, e.getMessage()))
        );
    }

    private ValidationErrorResponse getErrorResponse(
            String mainMessage,
            List<ErrorDetails> errorMessages
    ){
        return new ValidationErrorResponse(
                mainMessage,
                new Date(),
                errorMessages
        );
    }
}
