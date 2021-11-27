package com.example.sdd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {

    private final String message;
    private final List<String> errors;
    private final HttpStatus status;
    private final Date timestamp;

}
