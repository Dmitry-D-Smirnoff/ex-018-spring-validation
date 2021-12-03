package com.example.sdd.validation;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(onlyExplicitlyIncluded = true)
public enum ValidatedAction {
    ACTION_UNDEFINED("Любая операция"),

    ACTION_COUNTRY_CREATE("Обычное создание страны, id передвать нельзя"),
    ACTION_COUNTRY_UPDATE("Обычное обновление страны, нужно проверить что название страны уникально"),

    ACTION_PERSON_CREATE("Обычное создание гражданина, id передвать нельзя"),
    ACTION_PERSON_UPDATE("Обычное обновление гражданина, нужно проверить что имя граждания уникально");

    private final String description;

    ValidatedAction(String description) {
        this.description = description;
    }

}

