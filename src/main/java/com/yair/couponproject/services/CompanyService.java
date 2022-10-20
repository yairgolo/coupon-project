package com.yair.couponproject.services;

import com.yair.couponproject.entities.Company;
import com.yair.couponproject.entities.Coupon;
import com.yair.couponproject.enums.Category;
import com.yair.couponproject.errors.exceptions.AppException;
import com.yair.couponproject.repositories.CompanyRepository;
import com.yair.couponproject.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;

    // add a coupon
    public Coupon addCoupon(Coupon coupon) throws AppException {
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), coupon.getCompanyId())) {
            throw new AppException("The company has a coupon with the same title");
        }
        if (coupon.getEndDate().isBefore(coupon.getStartDate())) {
            throw new AppException("The start date is before the end date");
        }
        return couponRepository.save(coupon);
    }

    // update a coupon
    public Coupon updateCoupon(Coupon coupon) throws AppException {
        Optional<Coupon> optCoupon = couponRepository.findById(coupon.getId());
        if (optCoupon.isEmpty()) {
            throw new AppException("the coupon is not found");
        }
        if (!(optCoupon.get().getCompanyId().equals(coupon.getCompanyId()))) {
            throw new AppException("you cant change the company");
        }
        return couponRepository.save(coupon);
    }

    // delete a coupon
    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    // get all coupons by company
    public List<Coupon> getAll(Long id) {
        return couponRepository.findByCompanyId(id);
    }

    // get all coupons by category
    public List<Coupon> getAllByCategory(Long id, Category category) {
        return couponRepository.findByCompanyIdAndCategory(id, category);
    }

    // get all coupons by max price
    public List<Coupon> getAllMaxPrice(Long id, double price) {
        return couponRepository.findByCompanyIdAndPriceIsLessThan(id, price);
    }

    // get one coupon by id
    public Coupon getCoupon(Long id) throws AppException {
        Coupon coupon = couponRepository.findById(id).orElse(null);
        if (coupon == null) {
            throw new AppException("the coupon is not found");
        }
        return coupon;
    }

    // get a company
    public Company getCompany(Long id) throws AppException {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new AppException("Company doesn't exist");
        }
        return company.get();
    }
}
