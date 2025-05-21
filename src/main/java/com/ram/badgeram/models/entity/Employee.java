package com.ram.badgeram.models.entity;


import com.ram.badgeram.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter


public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeNumber;
    private String lastName;
    private String firstName;

    @Getter
    private boolean deleted;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "company_ref")
    private Company company;

    @OneToMany(mappedBy = "employee")
    private List<Badge> badges;

    @OneToMany(mappedBy = "employee")
    private List<Notification> notifications;
}