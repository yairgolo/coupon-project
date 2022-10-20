package com.yair.couponproject.controllers;

import com.yair.couponproject.dto.JwtDto;
import com.yair.couponproject.entities.User;
import com.yair.couponproject.errors.exceptions.AppException;
import com.yair.couponproject.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("login")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    // ------------- http request for login -------------

    private final AuthService authService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public JwtDto authenticate(@RequestBody final User user) throws AppException {
        return authService.authenticate(user);
    }
}