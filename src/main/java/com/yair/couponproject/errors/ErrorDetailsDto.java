package com.yair.couponproject.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetailsDto {

    // wrapper for exception
    private String errorMsg;
}
