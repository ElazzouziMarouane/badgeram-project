package com.ram.badgeram.repositories;

import com.ram.badgeram.models.entity.Badge;
import com.ram.badgeram.models.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRep extends JpaRepository<Notification, Long> {

    Page<Notification> findByDeletedFalse(Pageable pageable);
    Page<Notification> findByDeletedTrue(Pageable pageable);
    @Query("SELECT n FROM Notification n WHERE " +
            "(LOWER(n.employee.firstName) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(n.employee.lastName) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND n.deleted = false")
    Page<Notification> searchByEmployeeName(@Param("search") String search, Pageable pageable);
}
