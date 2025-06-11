package com.ram.badgeram.models.mapper;


import com.ram.badgeram.models.dto.CityDTO;
import com.ram.badgeram.models.dto.CityTempDTO;
import com.ram.badgeram.models.entity.City;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = AirportMapper.class)
public interface CityMapper {
    @Mapping(target = "airports", source = "airports")
    @Mapping(target = "country", source = "country")
    CityDTO toDTO(City entity);
    City toEntity(CityDTO dto);

    @Mapping(target = "country", source = "country")
    CityTempDTO toTempDTO(City city);


}