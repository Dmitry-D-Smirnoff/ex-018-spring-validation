package com.example.sdd.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CountryDto {

    private Integer id;

    @NotNull(message = "Наименование страны не может быть пустым")
    @Size(min=3, max=10, message = "Наименование страны должно быть от 3 до 10 символов")
    private String countryName;

    @JsonManagedReference
    private List<CityDto> cities;

}
