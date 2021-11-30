package com.example.sdd.validation;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(onlyExplicitlyIncluded = true)
public enum ValidatedOperation {
    UNDEFINED("Любая операция"),

    COUNTRY_CREATE("Обычное создание страны, id передвать нельзя"),
    COUNTRY_UPDATE("Обычное обновление страны, нужно проверить что название страны уникально"),

    PERSON_CREATE("Обычное создание гражданина, id передвать нельзя"),
    PERSON_UPDATE("Обычное обновление гражданина, нужно проверить что имя граждания уникально");

    private final String description;

    ValidatedOperation(String description) {
        this.description = description;
    }

}

