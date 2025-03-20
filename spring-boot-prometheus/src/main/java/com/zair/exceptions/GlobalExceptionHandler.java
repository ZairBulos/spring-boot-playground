package com.zair.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CheckoutException.class)
    public ResponseEntity<String> handleCheckoutException(CheckoutException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Error checkout: " + ex.getMessage());
    }
}
