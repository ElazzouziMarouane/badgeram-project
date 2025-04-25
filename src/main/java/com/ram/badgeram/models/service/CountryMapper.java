package com.ram.badgeram.models.service;

import com.ram.badgeram.models.dto.CountryDTO;
import com.ram.badgeram.models.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface CountryMapper {
    CountryDTO toDTO(Country country);
    Country toEntity(CountryDTO countryDTO);

    @Named("NotDeletedCountries")
    default List<CountryDTO> toDtoListNotDeleted(List<Country> countries) {
        if (countries == null) return null;
        return countries.stream()
                .filter(c -> !c.isDeleted())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}