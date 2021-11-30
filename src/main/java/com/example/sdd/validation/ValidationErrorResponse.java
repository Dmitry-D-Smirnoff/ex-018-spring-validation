package com.example.sdd.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {

    private final String mainMessage;
    private final Date timestamp;
    private final List<ErrorDetails> errors;

}
