package com.ram.badgeram.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class NotificationDTO {
    private Long id;
    private String message;
    private LocalDateTime date;
    private Boolean read;
    private EmployeeDTO employee;
}