package com.ram.badgeram.models.mapper;

import com.ram.badgeram.models.dto.*;
import com.ram.badgeram.models.entity.*;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface AirportMapper {
    AirportDTO toDTO(Airport airport);
    Airport toEntity(AirportDTO airportDTO);

    @Named("NotDeletedAirports")
    default List<AirportDTO> toDtoListNotDeleted(List<Airport> airports) {
        if (airports == null) return null;
        return airports.stream()
                .filter(e -> !e.isDeleted())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}