package com.example.sdd.entity.validation;

import com.example.sdd.dto.validation.group.PersonCreate;
import com.example.sdd.entity.PersonEntity;
import com.example.sdd.service.PersonService;
import com.example.sdd.validation.ErrorDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.sdd.validation.ErrorCode.ERROR_CODE_PERSON_NAME_DUPLICATION;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_PERSON_MUST_HAVE_UNIQUE_PERSON_NAME;

@Service
public class PersonEntityValidator implements EntityValidator<PersonEntity> {

    private final PersonService personService;

    public PersonEntityValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public List<ErrorDetails> collectValidationErrors(PersonEntity target, Class<?> hint, Class<PersonEntity> targetType) {
        List<ErrorDetails> errors = new ArrayList<>();

        List<PersonEntity> duplicates = personService.getPersonByName(target.getPersonName());
        if (!CollectionUtils.isEmpty(duplicates) && (PersonCreate.class.equals(hint)
                || duplicates.size() > 1 || !Objects.equals(duplicates.get(0).getId(), target.getId()))) {
            errors.add(new ErrorDetails(
                    ERROR_CODE_PERSON_NAME_DUPLICATION,
                    VALID_PERSON_MUST_HAVE_UNIQUE_PERSON_NAME,
                    "personName"
            ));
        }

        return errors;
    }

}
