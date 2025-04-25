package com.ram.badgeram.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class BadgeDTO {
    private Long id;
    private EmployeeDTO employee;
    private CompanyDTO company;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}