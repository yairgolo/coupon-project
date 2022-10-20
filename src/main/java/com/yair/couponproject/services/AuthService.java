package com.yair.couponproject.services;

import com.yair.couponproject.dto.JwtDto;
import com.yair.couponproject.entities.User;
import com.yair.couponproject.enums.Role;
import com.yair.couponproject.errors.exceptions.AppException;
import com.yair.couponproject.repositories.CompanyRepository;
import com.yair.couponproject.repositories.CustomerRepository;
import com.yair.couponproject.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    // ------------- login service -------------

    private final AuthenticationManager authenticationManager;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    public JwtDto authenticate(final User user) throws AppException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
            if (user.getRole().equals(Role.Company)) {
                return new JwtDto(JwtUtil.generateToken(companyRepository.findByEmail(user.getEmail())));
            } else if (user.getRole().equals(Role.Customer)) {
                return new JwtDto(JwtUtil.generateToken(customerRepository.findByEmail(user.getEmail())));
            }
            return new JwtDto(JwtUtil.generateToken(user.getEmail()));
        } catch (final Exception e) {
            throw new AppException("The email or password is incorrect, Please try again...");
        }
    }
}