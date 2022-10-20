package com.yair.couponproject.errors;

import com.yair.couponproject.errors.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionHandler {

    // exception handler for custom exception

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AppException.class)
    public ErrorDetailsDto couponSystemException(AppException e) {
        return new ErrorDetailsDto(e.getMessage());
    }
}
