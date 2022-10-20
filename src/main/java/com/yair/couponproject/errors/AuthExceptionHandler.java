package com.yair.couponproject.errors;

import com.yair.couponproject.errors.exceptions.AppException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RestControllerAdvice
public class AuthExceptionHandler implements ErrorController {

    private static final String PATH = "/error";

    // exception for authorization errors (JWT expired, no role choose and more...)
    @RequestMapping(PATH)
    public ResponseEntity<ErrorDetailsDto> handleError(final HttpServletRequest request,
                                                       final HttpServletResponse response) throws Throwable {
        throw new AppException("Failed, please log in again...");
    }

    public String getErrorPath() {
        return PATH;
    }
}
