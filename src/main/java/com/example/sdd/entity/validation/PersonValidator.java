package com.example.sdd.entity.validation;

import com.example.sdd.entity.Person;
import com.example.sdd.service.PersonService;
import com.example.sdd.validation.ErrorDetails;
import com.example.sdd.validation.ValidatedAction;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.sdd.validation.ErrorCode.ERROR_CODE_PERSON_NAME_DUPLICATION;
import static com.example.sdd.validation.ValidatedAction.ACTION_PERSON_CREATE;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_PERSON_MUST_HAVE_UNIQUE_PERSON_NAME;

@Service
public class PersonValidator implements EntityValidator<Person> {

    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public List<ErrorDetails> collectValidationErrors(Person person, ValidatedAction operation, Class type) {
        List<ErrorDetails> errors = new ArrayList<>();

        List<Person> duplicates = personService.getPersonByName(person.getPersonName());
        if (!CollectionUtils.isEmpty(duplicates) && (ACTION_PERSON_CREATE.equals(operation)
                || duplicates.size() > 1 || !Objects.equals(duplicates.get(0).getId(), person.getId()))) {
            errors.add(new ErrorDetails(
                    ERROR_CODE_PERSON_NAME_DUPLICATION,
                    VALID_PERSON_MUST_HAVE_UNIQUE_PERSON_NAME,
                    "personName"
            ));
        }

        return errors;
    }

}
