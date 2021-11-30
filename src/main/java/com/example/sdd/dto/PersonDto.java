package com.example.sdd.dto;

import com.example.sdd.dto.validation.group.PersonDtoUpdateGroup;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NotNull
@Getter
@Setter
public class PersonDto {
    @NotNull(groups = {PersonDtoUpdateGroup.class}, message = "Id обновляемого гражданина должен быть указан")
    private Integer id;

    @NotNull(message = "Имя Гражданина не может быть пустым")
    @Size(min=2, max=8, message = "Имя Гражданина должно быть от 2 до 8 символов")
    private String personName;

    @JsonBackReference
    private CountryDto country;

}