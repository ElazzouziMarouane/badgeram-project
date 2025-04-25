package com.ram.badgeram.models.service;

import com.ram.badgeram.models.dto.EmployeeDTO;
import com.ram.badgeram.models.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class, BadgeMapper.class, NotificationMapper.class})
public interface EmployeeMapper {
    EmployeeDTO toDTO(Employee employee);
    Employee toEntity(EmployeeDTO employeeDTO);

    @Named("NotDeletedEmployees")
    default List<EmployeeDTO> toDtoListNotDeleted(List<Employee> employees) {
        if (employees == null) return null;
        return employees.stream()
                .filter(e -> !e.isDeleted())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}