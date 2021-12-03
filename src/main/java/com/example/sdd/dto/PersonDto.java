package com.example.sdd.dto;

import com.example.sdd.dto.validation.group.PersonDtoCreateGroup;
import com.example.sdd.dto.validation.group.PersonDtoUpdateGroup;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import static com.example.sdd.validation.ValidationErrorMessages.VALID_PERSON_DTO_CREATE_MUST_HAVE_NULL_ID;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_PERSON_DTO_MUST_HAVE_COUNTRY_ID;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_PERSON_DTO_MUST_HAVE_PERSON_NAME;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_PERSON_DTO_NAME_MUST_BE_FROM_2_TO_8_CHARS;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_PERSON_DTO_UPDATE_MUST_HAVE_ID;

@NotNull
@Getter
@Setter
public class PersonDto {
    @Null(groups = {PersonDtoCreateGroup.class}, message = VALID_PERSON_DTO_CREATE_MUST_HAVE_NULL_ID)
    @NotNull(groups = {PersonDtoUpdateGroup.class}, message = VALID_PERSON_DTO_UPDATE_MUST_HAVE_ID)
    private Integer id;

    @NotNull(groups = {PersonDtoCreateGroup.class, PersonDtoUpdateGroup.class}, message = VALID_PERSON_DTO_MUST_HAVE_PERSON_NAME)
    @Size(min=2, max=8, groups = {PersonDtoCreateGroup.class, PersonDtoUpdateGroup.class}, message = VALID_PERSON_DTO_NAME_MUST_BE_FROM_2_TO_8_CHARS)
    private String personName;

    @NotNull(groups = {PersonDtoCreateGroup.class, PersonDtoUpdateGroup.class}, message = VALID_PERSON_DTO_MUST_HAVE_COUNTRY_ID)
    @JsonBackReference
    private CountryDto country;

}