package com.ram.badgeram.models.mapper;

import com.ram.badgeram.models.dto.CompanyDTO;
import com.ram.badgeram.models.dto.CompanyTempDTO;
import com.ram.badgeram.models.dto.CountryDTO;
import com.ram.badgeram.models.dto.CountryTempDTO;
import com.ram.badgeram.models.entity.Company;
import com.ram.badgeram.models.entity.Country;
import com.ram.badgeram.models.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" ,uses = EmployeeMapper.class)
public interface CompanyMapper {
    @Mapping(target = "employees", source = "employees")
    CompanyDTO toDTO(Company company);

    Company toEntity(CompanyDTO companyDTO);





}

