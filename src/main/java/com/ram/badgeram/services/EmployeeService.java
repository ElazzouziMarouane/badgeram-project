package com.ram.badgeram.services;

import com.ram.badgeram.models.dto.EmployeeDTO;
import com.ram.badgeram.models.entity.Company;
import com.ram.badgeram.models.entity.Employee;
import com.ram.badgeram.models.mapper.EmployeeMapper;
import com.ram.badgeram.repositories.CompanyRep;
import com.ram.badgeram.repositories.EmployeeRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final CompanyRep companyRep;
    private final EmployeeRep employeeRep;
    private final EmployeeMapper employeeMapper;

    public Page<EmployeeDTO> getAll(Pageable pageable) {
        return employeeRep.findByDeletedFalse(pageable)
                .map(employeeMapper::toDTO);
    }
    public Page<EmployeeDTO> findDeleted(Pageable pageable) {
        return employeeRep.findByDeletedTrue(pageable)
                .map(employeeMapper::toDTO);
    }
    public void restore(Long id) {
        Employee employee = employeeRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found by this ID : " + id));
        employee.setDeleted(false);
        employeeRep.save(employee);
    }
    public Page<EmployeeDTO> searchByName(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return employeeRep.findByDeletedFalse(pageable)
                    .map(employeeMapper::toDTO);
        } else {
            return employeeRep.searchByFirstOrLastName(keyword, pageable)
                    .map(employeeMapper::toDTO);
        }
    }


    public EmployeeDTO create(EmployeeDTO dto) {
        Employee employee = employeeMapper.toEntity(dto);
        employee.setDeleted(false);

        if (dto.getCompany() != null &&  dto.getCompany().getId() != null) {
            Company company = companyRep.findById(dto.getCompany().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Company not found with id " + dto.getCompany().getId()));
            employee.setCompany(company);

            if (company.getEmployees() == null) {
                company.setEmployees(new ArrayList<>());
            }

            employeeRep.save(employee);
            company.getEmployees().add(employee);
            companyRep.save(company);
        } else {
            employeeRep.save(employee);
        }

        return employeeMapper.toDTO(employee);
    }

    public EmployeeDTO update(Long id, EmployeeDTO employeeDTO) {
        Employee existing = employeeRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found by this ID : " + id));
        Employee updated = employeeMapper.toEntity(employeeDTO);
        updated.setId(existing.getId());
        updated.setDeleted(existing.isDeleted());
        return employeeMapper.toDTO(employeeRep.save(updated));
    }

    public void delete(Long id) {
        Employee employee = employeeRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found by this ID : " + id));

        employee.setDeleted(true);

        Company company = employee.getCompany();
        if (company != null && company.getEmployees() != null) {
            company.getEmployees().removeIf(e -> e.getId().equals(employee.getId()));
            companyRep.save(company);
        }

        employeeRep.save(employee);
    }

}
