package com.example.sdd.entity.validation;

import com.example.sdd.dao.PersonDao;
import com.example.sdd.dto.PersonDto;
import com.example.sdd.entity.Person;
import com.example.sdd.validation.ValidatedAction;
import com.example.sdd.validation.ValidatedActionException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static com.example.sdd.validation.ValidatedAction.ACTION_PERSON_CREATE;
import static com.example.sdd.validation.ValidatedAction.ACTION_UNDEFINED;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_PERSON_MUST_HAVE_UNIQUE_PERSON_NAME;

@Service
public class DeprecatedPersonEntityValidator implements SmartValidator {

    private final PersonDao personDao;

    public DeprecatedPersonEntityValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return PersonDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, ACTION_UNDEFINED);
    }

    @Override
    public void validate(@NotNull Object target, Errors errors, Object... validationHints){
        try{
            HashSet<ValidatedAction> actions = new HashSet<>(Arrays.asList(Arrays.copyOf(validationHints, validationHints.length, ValidatedAction[].class)));
            Person person = (Person) target;

            List<Person> duplicates = personDao.findByName(person.getPersonName());
            if (!CollectionUtils.isEmpty(duplicates) && (actions.contains(ACTION_PERSON_CREATE)
                    || duplicates.size() > 1 || !Objects.equals(duplicates.get(0).getId(), person.getId()))) {
                errors.rejectValue("personName", VALID_PERSON_MUST_HAVE_UNIQUE_PERSON_NAME);
            }

        } catch (ClassCastException e){
            throw new ValidatedActionException(e.getMessage());
        }
    }

}
