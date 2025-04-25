package com.ram.badgeram.models.service;

import com.ram.badgeram.models.dto.CityDTO;
import com.ram.badgeram.models.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, AirportMapper.class})
public interface CityMapper {
    CityDTO toDTO(City city);
    City toEntity(CityDTO cityDTO);

    @Named("NotDeletedCities")
    default List<CityDTO> toDtoListNotDeleted(List<City> cities) {
        if (cities == null) return null;
        return cities.stream()
                .filter(c -> !c.isDeleted())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
