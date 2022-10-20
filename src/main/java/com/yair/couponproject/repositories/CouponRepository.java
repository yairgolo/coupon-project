package com.yair.couponproject.repositories;

import com.yair.couponproject.entities.Coupon;
import com.yair.couponproject.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    // send coupon command to database


    boolean existsByTitleAndCompanyId(String title, Long companyId);

    void deleteByCompanyId(Long companyId);

    List<Coupon> findByCompanyId(Long companyId);

    List<Coupon> findByCompanyIdAndCategory(Long companyId, Category category);

    List<Coupon> findByCompanyIdAndPriceIsLessThan(Long companyId, double price);

}
