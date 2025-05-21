package com.ram.badgeram.services;

import com.ram.badgeram.models.dto.CountryDTO;
import com.ram.badgeram.models.entity.Country;
import com.ram.badgeram.models.mapper.CountryMapper;
import com.ram.badgeram.repositories.CountryRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRep countryRep;
    private final CountryMapper countryMapper;

    public Page<CountryDTO> getAll(Pageable pageable) {
        return countryRep.findByDeletedFalse(pageable).map(countryMapper::toDTO);
    }
    public Page<CountryDTO> findDeleted(Pageable pageable) {
        return countryRep.findByDeletedTrue(pageable)
                .map(countryMapper::toDTO);
    }
    public void restore(Long id) {
        Country country = countryRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("country non trouvé avec l'ID : " + id));
        country.setDeleted(false);
        countryRep.save(country);
    }
    public Page<CountryDTO> search(String name, Pageable pageable) {
        return countryRep.findByNameContainingIgnoreCaseAndDeletedFalse(name, pageable).map(countryMapper::toDTO);
    }

    public CountryDTO create(CountryDTO dto) {
        return countryMapper.toDTO(countryRep.save(countryMapper.toEntity(dto)));
    }

    public CountryDTO update(Long id, CountryDTO dto) {
        Country country = countryRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("country non trouvé avec l'ID : " + id));
        country.setName(dto.getName());
        return countryMapper.toDTO(countryRep.save(country));
    }

    public void delete(Long id) {
        Country country = countryRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("country non trouvé avec l'ID : " + id));
        country.setDeleted(true);
        countryRep.save(country);
    }
}
