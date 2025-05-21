package com.ram.badgeram.services;

import com.ram.badgeram.models.dto.BadgeDTO;
import com.ram.badgeram.models.dto.CompanyDTO;
import com.ram.badgeram.models.entity.Badge;
import com.ram.badgeram.models.entity.Company;
import com.ram.badgeram.models.mapper.CompanyMapper;
import com.ram.badgeram.repositories.CompanyRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRep companyRep;
    private final CompanyMapper companyMapper;

    public Page<CompanyDTO> getAll(Pageable pageable) {
        return companyRep.findByDeletedFalse(pageable).map(companyMapper::toDTO);
    }
    public Page<CompanyDTO> findDeleted(Pageable pageable) {
        return companyRep.findByDeletedTrue(pageable)
                .map(companyMapper::toDTO);
    }
    public void restore(Long id) {
        Company company = companyRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("company not found by this ID : " + id));
        company.setDeleted(false);
        companyRep.save(company);
    }
    public Page<CompanyDTO> search(String name, Pageable pageable) {
        return companyRep.findByNameContainingIgnoreCaseAndDeletedFalse(name, pageable).map(companyMapper::toDTO);
    }

    public CompanyDTO create(CompanyDTO dto) {
        Company company = companyMapper.toEntity(dto);
        Company saved = companyRep.save(company);
        return companyMapper.toDTO(saved);
    }

    public CompanyDTO update(Long id, CompanyDTO dto) {
        Company existing = companyRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("company not found by this ID : " + id));
        existing.setName(dto.getName());
        return companyMapper.toDTO(companyRep.save(existing));
    }

    public void delete(Long id) {
        Company company = companyRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("company not found by this ID : " + id));
        company.setDeleted(true);
        companyRep.save(company);
    }
}
