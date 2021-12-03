package com.example.sdd.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {

    private final String mainMessage;
    private final LocalDateTime localDateTime;
    private final List<ErrorDetails> errors;

}
