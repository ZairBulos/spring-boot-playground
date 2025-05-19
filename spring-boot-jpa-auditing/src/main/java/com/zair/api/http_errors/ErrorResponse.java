package com.zair.api.http_errors;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String error;
    private final String message;
    private final Integer status;

    public ErrorResponse(Exception ex, Integer status) {
        this.error = ex.getClass().getSimpleName();
        this.message = ex.getMessage();
        this.status = status;
    }

    public ErrorResponse(String error, String message, Integer status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }
}
