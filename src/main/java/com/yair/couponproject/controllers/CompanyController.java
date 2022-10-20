package com.yair.couponproject.controllers;

import com.yair.couponproject.entities.Company;
import com.yair.couponproject.entities.Coupon;
import com.yair.couponproject.enums.Category;
import com.yair.couponproject.errors.exceptions.AppException;
import com.yair.couponproject.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("company")
@CrossOrigin
public class CompanyController  {

    // ------------- http request for company -------------

    private final CompanyService companyService;

    // add coupon request
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-coupon")
    public Coupon createCoupon(@RequestBody Coupon coupon) throws AppException {
        return companyService.addCoupon(coupon);
    }

    // update coupon request
    @PutMapping("/update-coupon")
    public Coupon updateCoupon(@RequestBody Coupon coupon) throws AppException {
        return companyService.updateCoupon(coupon);
    }

    // delete coupon request
    @DeleteMapping("/delete-coupon/{id}")
    public void deleteCoupon(@PathVariable Long id) {
        companyService.deleteCoupon(id);
    }

    // get all coupons request
    @GetMapping("/get-all-coupons/{id}")
    public List<Coupon> getAllCoupons(@PathVariable Long id) {
        return companyService.getAll(id);
    }

    // get all coupon by category request
    @GetMapping("/get-all-coupons-category/{id}/{category}")
    public List<Coupon> getAllByCategory(@PathVariable Long id, @PathVariable Category category) {
        return companyService.getAllByCategory(id, category);
    }

    // get all coupons by max price request
    @GetMapping("/get-all-coupons-max-price/{id}/{price}")
    public List<Coupon> getAllMaxPrice(@PathVariable Long id, @PathVariable Double price) {
        return companyService.getAllMaxPrice(id, price);
    }

    // get one coupon request
    @GetMapping("/get-coupon/{id}")
    public Coupon getCouponById(@PathVariable Long id) throws AppException {
        return companyService.getCoupon(id);
    }

    // get company request
    @GetMapping("/get-company/{id}")
    public Company getCompany(@PathVariable Long id) throws AppException {
        return companyService.getCompany(id);
    }
}
