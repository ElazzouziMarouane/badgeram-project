package com.ram.badgeram.services;
import com.ram.badgeram.models.dto.BadgeDTO;
import com.ram.badgeram.models.dto.CityDTO;
import com.ram.badgeram.models.entity.Badge;
import com.ram.badgeram.models.entity.City;
import com.ram.badgeram.models.mapper.CityMapper;
import com.ram.badgeram.repositories.CityRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRep cityRep;
    private final CityMapper cityMapper;

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
        return cityMapper.toDTO(cityRep.save(city));
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
        cityRep.save(city);
    }
}

