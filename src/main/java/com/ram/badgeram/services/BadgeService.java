package com.ram.badgeram.services;
import com.ram.badgeram.models.dto.BadgeDTO;
import com.ram.badgeram.models.dto.CountryDTO;
import com.ram.badgeram.models.dto.EmployeeDTO;
import com.ram.badgeram.models.entity.Badge;
import com.ram.badgeram.models.entity.Country;
import com.ram.badgeram.models.entity.Employee;
import com.ram.badgeram.models.mapper.BadgeMapper;
import com.ram.badgeram.repositories.BadgeRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRep badgeRep;
    private final BadgeMapper badgeMapper;

    public Page<BadgeDTO> getAll(Pageable pageable) {
        return badgeRep.findByDeletedFalse(pageable)
                .map(badgeMapper::toDTO);
    }
    public Page<BadgeDTO> findDeleted(Pageable pageable) {
        return badgeRep.findByDeletedTrue(pageable)
                .map(badgeMapper::toDTO);
    }
    public void restore(Long id) {
        Badge badge = badgeRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Badge not found by this ID : " + id));
        badge.setDeleted(false);
        badgeRep.save(badge);
    }
    public Page<BadgeDTO> searchByEmployee(String name, Pageable pageable) {
        if (name == null || name.trim().isEmpty()) {
            return badgeRep.findByDeletedFalse(pageable)
                    .map(badgeMapper::toDTO);
        } else {
            return badgeRep.searchByEmployeeName(name, pageable)
                    .map(badgeMapper::toDTO);
        }
    }

    public BadgeDTO create(BadgeDTO dto) {
        return badgeMapper.toDTO(badgeRep
                .save(badgeMapper.toEntity(dto)));
    }
    public BadgeDTO update(Long id, BadgeDTO dto) {
        Badge existing = badgeRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Badge not found with ID: " + id));

        Badge updated = badgeMapper.toEntity(dto);
        updated.setId(existing.getId());
        updated.setDeleted(existing.isDeleted());

        return badgeMapper.toDTO(badgeRep.save(updated));
    }
    public void delete(Long id) {
        Badge badge = badgeRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Badge not found with ID: " + id));
        badge.setDeleted(true);
        badgeRep.save(badge);
    }


}

