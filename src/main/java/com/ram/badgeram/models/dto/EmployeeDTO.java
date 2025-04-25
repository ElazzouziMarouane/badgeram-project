package com.ram.badgeram.models.dto;

import com.ram.badgeram.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String employeeNumber;
    private String lastName;
    private String firstName;
    private Role role;
    private CompanyDTO company;
    private List<BadgeDTO> badges;
    private int unreadNotificationCount;
}

