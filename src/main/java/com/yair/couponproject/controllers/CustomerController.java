package com.yair.couponproject.controllers;

import com.yair.couponproject.entities.Coupon;
import com.yair.couponproject.entities.Customer;
import com.yair.couponproject.enums.Category;
import com.yair.couponproject.errors.exceptions.AppException;
import com.yair.couponproject.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {

    // ------------- http request for customer -------------

    private final CustomerService customerService;

    // purchase coupon request
    @PostMapping("/buy-coupon/{customerId}/{couponId}")
    public Coupon purchaseCoupon(@PathVariable Long customerId, @PathVariable Long couponId) throws AppException {
        return customerService.purchaseCoupon(customerId, couponId);
    }

    // get all purchased coupons request
    @GetMapping("/get-my-coupons/{id}")
    public List<Coupon> getBuyCoupons(@PathVariable Long id) throws AppException {
        return customerService.getAllPurchaseCoupon(id);
    }

    // get all purchased coupons by category request
    @GetMapping("/get-my-coupon-category/{id}/{category}")
    public List<Coupon> getBuyCouponsByCategory(@PathVariable Long id, @PathVariable Category category) throws AppException {
        return customerService.getAllPurchaseByCategory(id, category);
    }

    // get all purchased coupons by max price request
    @GetMapping("/get-my-coupon-price/{id}/{price}")
    public List<Coupon> getBuyCouponsByMaxPrice(@PathVariable Long id, @PathVariable Double price) throws AppException {
        return customerService.getAllPurchaseByMaxPrice(id, price);
    }

    // get all coupons request
    @GetMapping("/get-all")
    public List<Coupon> getAllCoupons() {
        return customerService.getAllCoupons();
    }

    // get customer request
    @GetMapping("/get-customer/{id}")
    public Customer getCustomer(@PathVariable Long id) throws AppException {
        return customerService.getCustomer(id);
    }


}
