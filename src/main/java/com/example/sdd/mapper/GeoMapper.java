package com.example.sdd.mapper;

import com.example.sdd.dto.PersonDto;
import com.example.sdd.dto.CountryDto;
import com.example.sdd.entity.CountryEntity;
import com.example.sdd.entity.PersonEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GeoMapper {

    private final ModelMapper modelMapper;

    public GeoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonDto convertToDto(PersonEntity personEntity) {
        return modelMapper.map(personEntity, PersonDto.class);
    }

    public PersonEntity convertToEntity(PersonDto personDto) {
        return modelMapper.map(personDto, PersonEntity.class);
    }

    public CountryDto convertToDto(CountryEntity countryEntity) {
        return modelMapper.map(countryEntity, CountryDto.class);
    }

    public CountryEntity convertToEntity(CountryDto countryDto) {
        return modelMapper.map(countryDto, CountryEntity.class);
    }

}
