package com.example.sdd.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {
    private Integer id;

    private String cityName;

    @JsonBackReference
    private CountryDto country;

}