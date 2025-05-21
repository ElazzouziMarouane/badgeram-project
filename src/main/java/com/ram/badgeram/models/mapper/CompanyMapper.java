package com.ram.badgeram.models.mapper;

import com.ram.badgeram.models.dto.CompanyDTO;
import com.ram.badgeram.models.entity.Company;
import com.ram.badgeram.models.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO toDTO(Company company);
    Company toEntity(CompanyDTO companyDTO);


    @Mapping(source = "employees", target = "employeeCount", qualifiedByName = "countActiveEmployees")

    @Named("countActiveEmployees")
    default int countActiveEmployees(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        return (int) employees.stream()
                .filter(employee -> !employee.isDeleted())
                .count();
    }
    @Named("NotDeletedCompanies")
    default List<CompanyDTO> toDtoListNotDeleted(List<Company> companies) {
        if (companies == null) return null;
        return companies.stream()
                .filter(c -> !c.isDeleted())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}


