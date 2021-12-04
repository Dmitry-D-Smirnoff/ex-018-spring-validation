package com.example.sdd.entity.validation;

import com.example.sdd.dto.validation.group.PersonCreate;
import com.example.sdd.entity.PersonEntity;
import com.example.sdd.service.PersonService;
import com.example.sdd.validation.ErrorDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.sdd.validation.ErrorCode.ERROR_CODE_PERSON_NAME_DUPLICATION;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_PERSON_MUST_HAVE_UNIQUE_PERSON_NAME;

@Service
public class PersonEntityValidator implements EntityValidator<PersonEntity> {

    private final PersonService personService;
    private final ValidatorFactory validatorFactory;

    public PersonEntityValidator(PersonService personService, ValidatorFactory validatorFactory) {
        this.personService = personService;
        this.validatorFactory = validatorFactory;
    }

    @Override
    public List<ErrorDetails> collectValidationErrors(PersonEntity target, Class<?> hint, Class<PersonEntity> targetType) {
        List<ErrorDetails> errors = validatorFactory.getValidator().validate(target)
                .stream().map(ErrorDetails::new).collect(Collectors.toList());

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
