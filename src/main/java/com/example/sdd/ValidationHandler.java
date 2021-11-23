package com.example.sdd;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( // error handle for @Valid
            MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            HttpStatus status, @NotNull WebRequest request
    ) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ObjectError::getCode)
                .collect(Collectors.toList());

        return getResponse(
                "MethodArgumentNotValidException",
                errors,
                status,
                headers
                );

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {

        List<String> messages = e.getConstraintViolations()
                .stream()
                .map(err -> "PATH: " + err.getPropertyPath().toString() +
                        ", ERROR: " + err.getMessage())
                .collect(Collectors.toList());

        return getResponse(
                "ConstraintViolationException",
                messages,
                HttpStatus.BAD_REQUEST,
                new HttpHeaders()
        );
    }

    private ResponseEntity<Object> getResponse(
            String mainMessage,
            List<String> errorMessages,
            HttpStatus status,
            HttpHeaders headers
    ){
        ValidationError error = new ValidationError(
                mainMessage,
                errorMessages,
                status,
                new Date()
        );

        return new ResponseEntity<>(error, headers, status);
    }
}
