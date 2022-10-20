package com.yair.couponproject.repositories;

import com.yair.couponproject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // send customer command to database
    boolean existsByEmail(String email);
    Customer findByEmail(String email);


}
