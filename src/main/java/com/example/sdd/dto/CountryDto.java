package com.example.sdd.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountryDto {

    private Integer id;

    private String countryName;

    @JsonManagedReference
    private List<CityDto> cities;

}
