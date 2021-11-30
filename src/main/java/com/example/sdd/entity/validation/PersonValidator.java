package com.example.sdd.entity.validation;

import com.example.sdd.entity.Person;
import com.example.sdd.service.PersonService;
import com.example.sdd.validation.ErrorCode;
import com.example.sdd.validation.ErrorDetails;
import com.example.sdd.validation.ValidatedOperation;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonValidator implements EntityValidator<Person> {

    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public List<ErrorDetails> collectValidationErrors(Person person, ValidatedOperation operation, Class type) {
        List<ErrorDetails> errors = new ArrayList<>();

        List<Person> duplicates = personService.getPersonByName(person.getPersonName());
        if (!CollectionUtils.isEmpty(duplicates) && ( ValidatedOperation.PERSON_CREATE.equals(operation)
                || duplicates.size() > 1 || !Objects.equals(duplicates.get(0).getId(), person.getId()))) {
            errors.add(new ErrorDetails(
                    ErrorCode.VALIDATION_001_PERSON_NAME_DUPLICATION,
                    "Такое имя Гражданина уже используется",
                    "personName"
            ));
        }

        return errors;
    }

}
