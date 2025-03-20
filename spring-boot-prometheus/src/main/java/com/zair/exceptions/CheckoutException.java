package com.zair.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CheckoutException extends RuntimeException {

    public CheckoutException() {
        super();
    }

    public CheckoutException(String message) {
        super(message);
    }

    public CheckoutException(Throwable cause) {
        super(cause);
    }

    public CheckoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
