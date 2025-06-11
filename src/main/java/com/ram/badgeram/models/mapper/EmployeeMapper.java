package com.ram.badgeram.models.mapper;

import com.ram.badgeram.models.dto.CityDTO;
import com.ram.badgeram.models.dto.CityTempDTO;
import com.ram.badgeram.models.dto.EmployeeDTO;
import com.ram.badgeram.models.dto.EmployeeTempDTO;
import com.ram.badgeram.models.entity.City;
import com.ram.badgeram.models.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {BadgeMapper.class,NotificationMapper.class})
public interface EmployeeMapper {
    @Mapping(target = "badges", source = "badges")
    @Mapping(target = "company", source = "company")
    EmployeeDTO toDTO(Employee employee);
    Employee toEntity(EmployeeDTO employeeDTO);

    @Mapping(target = "company", source = "company" )
    EmployeeTempDTO toTempDTO(Employee employee);




}