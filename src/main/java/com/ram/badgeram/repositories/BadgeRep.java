package com.ram.badgeram.repositories;

import com.ram.badgeram.models.entity.Badge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRep extends JpaRepository<Badge, Long> {

    Page<Badge> findByDeletedFalse(Pageable pageable);
    Page<Badge> findByDeletedTrue(Pageable pageable);

    @Query("SELECT b FROM Badge b WHERE " +
            "(LOWER(b.employee.firstName) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(b.employee.lastName) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND b.deleted = false")
    Page<Badge> searchByEmployeeName(@Param("search") String search, Pageable pageable);
}
