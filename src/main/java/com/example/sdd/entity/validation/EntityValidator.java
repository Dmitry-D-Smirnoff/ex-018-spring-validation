package com.example.sdd.entity.validation;

import com.example.sdd.validation.ErrorDetails;
import com.example.sdd.validation.ValidatedOperation;
import com.example.sdd.validation.ValidationException;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

interface EntityValidator<T> {

    List<ErrorDetails> collectValidationErrors(@NotNull T object, ValidatedOperation operation, Class<T> type);

    default void validate(@NotNull T object, ValidatedOperation operation, Class<T> type) {
        List<ErrorDetails> errors = collectValidationErrors(object, operation, type);

        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationException("Ошибки валидации объекта " + type.getName(), errors);
        }
    }
}
