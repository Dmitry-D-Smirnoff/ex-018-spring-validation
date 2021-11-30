package com.example.sdd.mapper;

import com.example.sdd.dto.PersonDto;
import com.example.sdd.dto.CountryDto;
import com.example.sdd.entity.Person;
import com.example.sdd.entity.Country;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GeoMapper {

    private final ModelMapper modelMapper;

    public GeoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonDto convertToDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    public Person convertToEntity(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    public CountryDto convertToDto(Country country) {
        return modelMapper.map(country, CountryDto.class);
    }

    public Country convertToEntity(CountryDto countryDto) {
        return modelMapper.map(countryDto, Country.class);
    }

}
