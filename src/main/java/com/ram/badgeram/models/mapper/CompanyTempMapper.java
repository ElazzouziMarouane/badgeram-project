package com.ram.badgeram.models.mapper;

import com.ram.badgeram.models.dto.CompanyTempDTO;
import com.ram.badgeram.models.entity.Company;

public interface CompanyTempMapper {
    CompanyTempDTO toTempDTO(Company company);
}
