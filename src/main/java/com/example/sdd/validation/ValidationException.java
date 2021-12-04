package com.example.sdd.validation;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private final List<ErrorDetails> errors;
    private final String mainMessage;
    private final LocalDateTime localDateTime;

    public ValidationException(String mainMessage, List<ErrorDetails> errors) {
        super(mainMessage);
        this.mainMessage = mainMessage;
        this.errors = errors;
        this.localDateTime = LocalDateTime.now();
    }

}
