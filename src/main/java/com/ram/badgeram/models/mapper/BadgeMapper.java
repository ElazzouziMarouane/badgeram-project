package com.ram.badgeram.models.mapper;

import com.ram.badgeram.models.dto.AirportDTO;
import com.ram.badgeram.models.dto.BadgeDTO;
import com.ram.badgeram.models.entity.Airport;
import com.ram.badgeram.models.entity.Badge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = CompanyMapper.class)
public interface BadgeMapper {
    BadgeDTO toDTO(Badge badge);
    Badge toEntity(BadgeDTO badgeDTO);


}