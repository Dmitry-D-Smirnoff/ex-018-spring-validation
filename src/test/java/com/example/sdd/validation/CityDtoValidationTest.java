package com.example.sdd.validation;

import com.example.sdd.dto.CityDto;
import com.example.sdd.dto.CountryDto;
import com.example.sdd.dto.validation.CountryDtoValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

import java.util.Arrays;

@SpringBootTest
public class CityDtoValidationTest {

    @Autowired
    private CountryDtoValidator countryDtoValidator;

    @Test
    public void countryMustBeNotNull() {

        final DataBinder dataBinder = new DataBinder(getCountryDtoWithNullCountryName());
        dataBinder.addValidators(countryDtoValidator);
        dataBinder.validate();

        if (dataBinder.getBindingResult().hasErrors()) {
            dataBinder
                    .getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getCode)
                    .forEach(System.out::println);
        }

        Assert.isTrue(
                dataBinder
                        .getBindingResult()
                        .hasErrors(),
                "ОШИБКА: Валидация без указания страны прошла успешно");

        Assert.isTrue(
                dataBinder
                        .getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(ObjectError::getCode)
                        .anyMatch("Наименование страны должно быть заполнено"::equals),
                "ОШИБКА: Не найдено сообщение об указании пустой страны"
        );

    }

    private CountryDto getCountryDtoWithNullCountryName(){
        final CountryDto countryDto = getCorrectCountryDto();
        countryDto.setCountryName(null);
        return countryDto;
    }

    private CountryDto getCorrectCountryDto(){
        CountryDto countryDto = new CountryDto();
        countryDto.setCountryName("Russia");

        CityDto cityDto = new CityDto();
        cityDto.setCityName("Moscow");

        countryDto.setCities(Arrays.asList(cityDto));
        return countryDto;
    }

}


