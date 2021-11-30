package com.example.sdd.entity.validation;

import com.example.sdd.entity.Person;
import com.example.sdd.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Service
public class PersonValidator implements Validator {

    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object obj, @NotNull Errors errors) {
        Person person = (Person) obj;

        if (!Objects.isNull(personService.getPersonByName(person.getPersonName()))) {
            errors.rejectValue("personName", "Такое наименование города уже существует");
        }



    }

}
