package com.zair.api.http_errors;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String error;
    private final String message;
    private final Integer code;

    public ErrorResponse(Exception ex, Integer code) {
        this.error = ex.getClass().getSimpleName();
        this.message = ex.getMessage();
        this.code = code;
    }
}
