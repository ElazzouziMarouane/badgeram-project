package com.ram.badgeram.models.mapper;

import com.ram.badgeram.models.dto.BadgeDTO;
import com.ram.badgeram.models.entity.Badge;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, CompanyMapper.class})
public interface BadgeMapper {
    BadgeDTO toDTO(Badge badge);
    Badge toEntity(BadgeDTO badgeDTO);

    @Named("NotDeletedBadges")
    default List<BadgeDTO> toDtoListNotDeleted(List<Badge> badges) {
        if (badges == null) return null;
        return badges.stream()
                .filter(b -> !b.isDeleted())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}