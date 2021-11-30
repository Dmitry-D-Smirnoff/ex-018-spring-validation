package com.example.sdd.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PersonDto {
    private Integer id;

    @NotNull(message = "Наименование города не может быть пустым")
    @Size(min=2, max=8, message = "Наименование страны должно быть от 2 до 8 символов")
    private String personName;

    @JsonBackReference
    private CountryDto country;

}