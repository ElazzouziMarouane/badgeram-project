package com.ram.badgeram.repositories;

import com.ram.badgeram.models.entity.Badge;
import com.ram.badgeram.models.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRep extends JpaRepository<Company,Long> {

    Page<Company> findByDeletedFalse(Pageable pageable);
    Page<Company> findByDeletedTrue(Pageable pageable);
    Page<Company> findByNameContainingIgnoreCaseAndDeletedFalse(String name, Pageable pageable);
}

