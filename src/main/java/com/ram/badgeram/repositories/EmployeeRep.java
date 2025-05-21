package com.ram.badgeram.repositories;

import com.ram.badgeram.models.entity.Badge;
import com.ram.badgeram.models.entity.Employee;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRep extends JpaRepository<Employee, Long> {
    Page<Employee> findByDeletedFalse(Pageable pageable);
    Page<Employee> findByDeletedTrue(Pageable pageable);
    @Query("SELECT e FROM Employee e WHERE " +
            "(LOWER(e.firstName) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND e.deleted = false")
    Page<Employee> searchByFirstOrLastName(@Param("search") String search, Pageable pageable);
}
