package com.example.sdd.dto;

import com.example.sdd.dto.validation.group.CountryCreate;
import com.example.sdd.dto.validation.group.CountryUpdate;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

import static com.example.sdd.validation.ValidationErrorMessages.VALID_COUNTRY_DTO_CREATE_MUST_HAVE_NULL_ID;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_COUNTRY_DTO_MUST_CONTAIN_AT_LEAST_ONE_PERSON_DTO;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_COUNTRY_DTO_MUST_HAVE_COUNTRY_NAME;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_COUNTRY_DTO_NAME_MUST_BE_FROM_3_TO_10_CHARS;
import static com.example.sdd.validation.ValidationErrorMessages.VALID_COUNTRY_DTO_UPDATE_MUST_HAVE_ID;

@NotNull
@Getter
@Setter
public class CountryDto {
    @Null(groups = {CountryCreate.class}, message = VALID_COUNTRY_DTO_CREATE_MUST_HAVE_NULL_ID)
    @NotNull(groups = {CountryUpdate.class}, message = VALID_COUNTRY_DTO_UPDATE_MUST_HAVE_ID)
    private Integer id;

    @NotNull(groups = {CountryCreate.class, CountryUpdate.class}, message = VALID_COUNTRY_DTO_MUST_HAVE_COUNTRY_NAME)
    @Size(min = 3, max = 10, groups = {CountryCreate.class, CountryUpdate.class}, message = VALID_COUNTRY_DTO_NAME_MUST_BE_FROM_3_TO_10_CHARS)
    private String countryName;

    @NotEmpty(groups = {CountryCreate.class, CountryUpdate.class}, message = VALID_COUNTRY_DTO_MUST_CONTAIN_AT_LEAST_ONE_PERSON_DTO)
    @Valid
    @JsonManagedReference
    private List<PersonDto> persons;

}
