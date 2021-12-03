package com.example.sdd.validation;

public final class ValidationErrorMessages {
    //Person DTO
    public static final String VALID_PERSON_DTO_CREATE_MUST_HAVE_NULL_ID = "Id создаваемого гражданина должен быть пустым";
    public static final String VALID_PERSON_DTO_UPDATE_MUST_HAVE_ID = "Id обновляемого гражданина должен быть указан";
    public static final String VALID_PERSON_DTO_MUST_HAVE_PERSON_NAME = "Имя гражданина не может быть пустым";
    public static final String VALID_PERSON_DTO_NAME_MUST_BE_FROM_2_TO_8_CHARS = "Имя гражданина должно быть от 2 до 8 символов";
    public static final String VALID_PERSON_DTO_NAME_MUST_BE_IN_RUSSIAN_WITH_FIRST_CAPITAL_LETTER = "Имя гражданина должно быть русскоязычным и начинаться с заглавной буквы";
    public static final String VALID_PERSON_DTO_MUST_HAVE_COUNTRY_ID = "Данные о гражданине должны содержать id страны";
    //Person Entity
    public static final String VALID_PERSON_MUST_HAVE_UNIQUE_PERSON_NAME = "Такое имя гражданина уже используется";
    //Country DTO
    public static final String VALID_COUNTRY_DTO_CREATE_MUST_HAVE_NULL_ID = "Id создаваемой страны должен быть пустым";
    public static final String VALID_COUNTRY_DTO_UPDATE_MUST_HAVE_ID = "Id обновляемой страны должен быть указан";
    public static final String VALID_COUNTRY_DTO_MUST_HAVE_COUNTRY_NAME = "Наименование страны не может быть пустым";
    public static final String VALID_COUNTRY_DTO_NAME_MUST_BE_FROM_3_TO_10_CHARS = "Наименование страны должно быть от 3 до 10 символов";
    public static final String VALID_COUNTRY_DTO_NAME_MUST_BE_IN_RUSSIAN_WITH_FIRST_CAPITAL_LETTER = "Наименование страны должно быть русскоязычным и начинаться с заглавной буквы";
    public static final String VALID_COUNTRY_DTO_MUST_CONTAIN_AT_LEAST_ONE_PERSON_DTO = "Перечень граждан страны должен включать хотя бы одного человека";
    //Country Entity
    public static final String VALID_COUNTRY_MUST_HAVE_UNIQUE_COUNTRY_NAME = "Такое наименование страны уже используется";
}
