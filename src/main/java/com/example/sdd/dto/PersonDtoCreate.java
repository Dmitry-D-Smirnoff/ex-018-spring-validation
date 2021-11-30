package com.example.sdd.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NotNull
@Getter
@Setter
public class PersonDtoCreate {
    @NotNull(message = "Имя Гражданина не может быть пустым")
    @Size(min=2, max=8, message = "Имя Гражданина должно быть от 2 до 8 символов")
    private String personName;

    @JsonBackReference
    private CountryDtoCreate country;
}