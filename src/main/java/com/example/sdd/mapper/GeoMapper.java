package com.example.sdd.mapper;

import com.example.sdd.dto.CityDto;
import com.example.sdd.dto.CountryDto;
import com.example.sdd.entity.City;
import com.example.sdd.entity.Country;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GeoMapper {

    private final ModelMapper modelMapper;

    public GeoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CityDto convertToDto(City city) {
        return modelMapper.map(city, CityDto.class);
    }

    public City convertToEntity(CityDto cityDto) {
        return modelMapper.map(cityDto, City.class);
    }

    public CountryDto convertToDto(Country country) {
        return modelMapper.map(country, CountryDto.class);
    }

    public Country convertToEntity(CountryDto countryDto) {
        return modelMapper.map(countryDto, Country.class);
    }

}
