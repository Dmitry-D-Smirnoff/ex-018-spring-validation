package com.example.sdd.validation;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

public class CustomValidationUtils {

    private static final Pattern SHOULD_CONTAIN_ONLY_RUSSIAN_LETTERS_AND_SPACES_PATTERN = Pattern.compile("[а-яёА-ЯЁ\\s]+");
    private static final Pattern SHOULD_START_WITH_CAPITAL_RUSSIAN_LETTER = Pattern.compile("^[А-Я][а-яёА-ЯЁ\\s]+");

    public static boolean containsOnlyRussianLettersAndSpaces(String personName) {
        return SHOULD_CONTAIN_ONLY_RUSSIAN_LETTERS_AND_SPACES_PATTERN.matcher(personName).matches();
    }

    public static boolean startsWithCapitalRussianLetter(String s) {
        return SHOULD_START_WITH_CAPITAL_RUSSIAN_LETTER.matcher(s).matches();
    }

    public static boolean allNotEmpty(Object... objects) {
        try {
            return Arrays.stream(objects)
                    .filter(a -> a.equals(a))
                    .filter(a -> a instanceof String)
                    .map(a -> (String) a)
                    .map(String::trim)
                    .noneMatch(StringUtils::isEmpty);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
