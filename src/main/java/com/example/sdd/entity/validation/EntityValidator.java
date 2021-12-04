package com.example.sdd.entity.validation;

import com.example.sdd.validation.ErrorDetails;
import com.example.sdd.validation.ValidationException;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

interface EntityValidator<T> {

    List<ErrorDetails> collectValidationErrors(@NotNull T target, Class<?> operation, Class<T> targetType);

    default void validate(@NotNull T target, Class<?> hint, Class<T> targetType) {
        List<ErrorDetails> errors = collectValidationErrors(target, hint, targetType);

        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationException("Ошибки валидации объекта " + targetType.getName(), errors);
        }
    }
}
