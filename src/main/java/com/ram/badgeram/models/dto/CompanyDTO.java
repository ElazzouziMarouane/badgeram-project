package com.ram.badgeram.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class CompanyDTO {
    private Long id;
    private String name;
    private int employeeCount;
}

