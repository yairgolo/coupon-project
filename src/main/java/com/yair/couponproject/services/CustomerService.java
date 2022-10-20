package com.yair.couponproject.services;

import com.yair.couponproject.entities.Coupon;
import com.yair.couponproject.entities.Customer;
import com.yair.couponproject.enums.Category;
import com.yair.couponproject.errors.exceptions.AppException;
import com.yair.couponproject.repositories.CouponRepository;
import com.yair.couponproject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;

    // purchase a coupon
    public Coupon purchaseCoupon(Long customerId, Long couponId) throws AppException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Coupon coupon = couponRepository.findById(couponId).orElse(null);
        if (customer == null) {
            throw new AppException("Customer not exist");
        }
        if (coupon == null) {
            throw new AppException("Coupon not exist");
        }
        for (Coupon c  : customer.getCoupons()) {
            if (c.getId() == couponId) {
                throw new AppException("The user has already purchased the coupon");
            }
        }
        if (coupon.getAmount() <= 0) {
            throw new AppException("There are no coupons in stock");
        }
        if (coupon.getEndDate().isBefore(LocalDate.now())) {
            throw new AppException("The coupon has expired");
        }
        int amountCoupon = coupon.getAmount();
        coupon.setAmount(amountCoupon - 1);
        couponRepository.save(coupon);
        customer.getCoupons().add(coupon);
        customerRepository.save(customer);
        return coupon;
    }

    // get all purchases coupons by customer
    public List<Coupon> getAllPurchaseCoupon(Long id) throws AppException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new AppException("customer is not found");
        }
        return customer.get().getCoupons();
    }

    // get all purchases by category
    public List<Coupon> getAllPurchaseByCategory(Long id, Category category) throws AppException {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            throw new AppException("customer is not found");
        }
        List<Coupon> coupons = new ArrayList<>();
        for (Coupon c : customer.getCoupons()) {
            if (c.getCategory() == category) {
                coupons.add(c);
            }
        }
        return coupons;
    }

    // get all purchases by max price
    public List<Coupon> getAllPurchaseByMaxPrice(Long id, double price) throws AppException {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            throw new AppException("customer is not found");
        }
        List<Coupon> coupons = new ArrayList<>();
        for (Coupon c : customer.getCoupons()) {
            if (c.getPrice() <= price) {
                coupons.add(c);
            }
        }
        return coupons;
    }

    public List<Coupon>  getAllCoupons() {
        return couponRepository.findAll();
    }

    // get a customer
    public Customer getCustomer(Long id) throws AppException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new AppException("the customer is doesn't exist");
        }
        return customer.get();
    }
}
