package com.ram.badgeram.services;
import com.ram.badgeram.models.dto.CityDTO;
import com.ram.badgeram.models.dto.CountryDTO;
import com.ram.badgeram.models.entity.City;
import com.ram.badgeram.models.entity.Country;
import com.ram.badgeram.models.mapper.CityMapper;
import com.ram.badgeram.repositories.CityRep;
import com.ram.badgeram.repositories.CountryRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRep cityRep;

    private static final Logger LOG = LoggerFactory.getLogger(CityService.class);
    private final CityMapper cityMapper;
    private final CountryRep countryRep;
    public Page<CityDTO> getAll(Pageable pageable) {
        return cityRep.findByDeletedFalse(pageable).map(cityMapper::toDTO);
    }
    public Page<CityDTO> findDeleted(Pageable pageable) {
        return cityRep.findByDeletedTrue(pageable)
                .map(cityMapper::toDTO);
    }
    public void restore(Long id) {
        City city = cityRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("city not found by this ID : " + id));
        city.setDeleted(false);
        cityRep.save(city);
    }
    public Page<CityDTO> search(String name, Pageable pageable) {
        return cityRep.findByNameContainingIgnoreCaseAndDeletedFalse(name, pageable).map(cityMapper::toDTO);
    }



    public CityDTO create(CityDTO dto) {
        City city = cityMapper.toEntity(dto);

        if (dto.getCountry() != null && dto.getCountry().getId()!=null) {
            Country country = countryRep.findById(dto.getCountry().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Country not found with id " + dto.getCountry().getId()));
            city.setCountry(country);

            if (country.getCities() == null) {
                country.setCities(new ArrayList<>());
            }
            cityRep.save(city);
            country.getCities().add(city);
            countryRep.save(country);}

        return cityMapper.toDTO(city);
    }
    public CityDTO update(Long id, CityDTO dto) {
        City city = cityRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("city not found by this ID : " + id));
        city.setName(dto.getName());
        return cityMapper.toDTO(cityRep.save(city));
    }

    public void delete(Long id) {
        City city = cityRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("city not found by this ID : " + id));

        city.setDeleted(true);

        Country country = city.getCountry();
        if (country != null && country.getCities() != null) {
            country.getCities().removeIf(c -> c.getId().equals(city.getId()));
            countryRep.save(country);
        }

        cityRep.save(city);
    }
}

