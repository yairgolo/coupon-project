package com.yair.couponproject.security;

import com.yair.couponproject.entities.Company;
import com.yair.couponproject.entities.Customer;
import com.yair.couponproject.repositories.CompanyRepository;
import com.yair.couponproject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    //---------- login security service ----------

    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        if (email.equals("admin@admin.com")) {
            return new org.springframework.security.core.userdetails.User(
                    "admin@admin.com",
                    "admin",
                    new ArrayList<>()
            );
        }
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            return new org.springframework.security.core.userdetails.User(
                    customer.getEmail(),
                    customer.getPassword(),
                    new ArrayList<>()
            );
        } else {
            Company company = companyRepository.findByEmail(email);
            return new org.springframework.security.core.userdetails.User(
                    company.getEmail(),
                    company.getPassword(),
                    new ArrayList<>()
            );
        }
    }
}