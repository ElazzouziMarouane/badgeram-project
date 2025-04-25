package com.ram.badgeram.models.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private boolean deleted;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    private Boolean read;

    @ManyToOne
    @JoinColumn(name = "employee_ref")
    private Employee employee;

}

