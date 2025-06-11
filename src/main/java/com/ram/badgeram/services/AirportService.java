package com.ram.badgeram.services;

import com.ram.badgeram.models.dto.AirportDTO;
import com.ram.badgeram.models.dto.CityDTO;
import com.ram.badgeram.models.entity.Airport;
import com.ram.badgeram.models.entity.City;
import com.ram.badgeram.models.entity.Country;
import com.ram.badgeram.models.mapper.AirportMapper;
import com.ram.badgeram.repositories.AirportRep;
import com.ram.badgeram.repositories.CityRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final CityRep cityRep;
    private final AirportRep airportRep;
    private final AirportMapper airportMapper;

    public Page<AirportDTO> getAll(Pageable pageable) {
        return airportRep.findByDeletedFalse(pageable).map(airportMapper::toDTO);
    }
    public Page<AirportDTO> findDeleted(Pageable pageable) {
        return airportRep.findByDeletedTrue(pageable)
                .map(airportMapper::toDTO);
    }
    public void restore(Long id) {
        Airport badge = airportRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Airport not found by this ID : " + id));
        badge.setDeleted(false);
        airportRep.save(badge);
    }
    public Page<AirportDTO> search(String keyword, Pageable pageable) {
        return airportRep.searchByNameOrCityName(keyword, pageable).map(airportMapper::toDTO);
    }

    //    public AirportDTO create(AirportDTO dto) {
//        return airportMapper.toDTO(airportRep.save(airportMapper.toEntity(dto)));
//    }
    public AirportDTO create(AirportDTO dto) {
        Airport airport = airportMapper.toEntity(dto);

        if (dto.getCity() != null && dto.getCity().getId()!=null) {
            City city = cityRep.findById(dto.getCity().getId())
                    .orElseThrow(() -> new EntityNotFoundException("City not found with id " + dto.getCity().getId()));
            airport.setCity(city);

            if (city.getAirports() == null) {
                city.setAirports(new ArrayList<>());
            }
            airportRep.save(airport);
            city.getAirports().add(airport);
            cityRep.save(city);}

        return airportMapper.toDTO(airport);
    }
    public AirportDTO update(Long id, AirportDTO dto) {
        Airport airport = airportRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Airport not found by this ID : " + id));
        airport.setName(dto.getName());
        return airportMapper.toDTO(airportRep.save(airport));
    }

    public void delete(Long id) {
        Airport airport = airportRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Airport not found by this ID : " + id));
        airport.setDeleted(true);
        airportRep.save(airport);
    }
}