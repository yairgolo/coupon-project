package com.yair.couponproject.task;

import com.yair.couponproject.entities.Coupon;
import com.yair.couponproject.repositories.CouponRepository;
import com.yair.couponproject.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class CouponExpirationDailyJob {
    private final CouponRepository couponRepository;
    private final CompanyService companyService;

    //------------- task to delete coupon with expired date ---------------

    @Scheduled(fixedDelay = 86400000)
    public void deleteExpiredCoupons() {
        List<Coupon> coupons = couponRepository.findAll();

        for (Coupon coupon : coupons) {
            if (coupon.getEndDate().isBefore(LocalDate.now())) {
                try {
                    companyService.deleteCoupon(coupon.getId());
                    System.out.println("coupon with id: " + coupon.getId() + " is deleted!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
