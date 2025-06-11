package com.ram.badgeram.models.mapper;

import com.ram.badgeram.models.dto.CountryDTO;
import com.ram.badgeram.models.dto.CountryTempDTO;
import com.ram.badgeram.models.entity.Country;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = CityMapper.class)
public interface CountryMapper {
    @Mapping(target = "cities", source = "cities")
    CountryDTO toDTO(Country country);

    Country toEntity(CountryDTO countryDTO);
    CountryTempDTO toTempDTO(Country country);




}