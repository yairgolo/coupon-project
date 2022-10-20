package com.yair.couponproject.repositories;

import com.yair.couponproject.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    // send company command to database
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    Company findByEmail(String email);




}
