package com.example.sdd.entity.validation;

import com.example.sdd.validation.ErrorDetails;
import com.example.sdd.validation.ValidationException;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface EntityValidator<T> {

    List<ErrorDetails> collectValidationErrors(@NotNull T target, Class<?> operation);

    default void validate(@NotNull T target, Class<?> hint) {
        List<ErrorDetails> errors = collectValidationErrors(target, hint);

        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationException("Ошибки валидации объекта " + target.getClass().getName(), errors);
        }
    }
}
