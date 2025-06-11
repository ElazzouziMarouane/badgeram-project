package com.ram.badgeram.models.mapper;

import com.ram.badgeram.models.dto.AirportDTO;
import com.ram.badgeram.models.entity.Airport;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface AirportMapper {
    @Mapping(target = "city", source = "city")
    AirportDTO toDTO(Airport entity);
    Airport toEntity(AirportDTO dto);



}
