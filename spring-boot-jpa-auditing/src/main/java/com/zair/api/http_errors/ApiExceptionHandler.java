package com.zair.api.http_errors;

import com.zair.services.exceptions.ConflictException;
import com.zair.services.exceptions.ForbiddenException;
import com.zair.services.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorResponse unauthorized(BadCredentialsException ex) {
        return new ErrorResponse(
                "BadCredentialsException",
                "Incorrect username or password.",
                HttpStatus.UNAUTHORIZED.value()
        );
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            ForbiddenException.class,
            org.springframework.security.access.AccessDeniedException.class
    })
    public ErrorResponse forbidden(Exception ex) {
        return new ErrorResponse(
                "ForbiddenException",
                "You do not have permission to access this resource.",
                HttpStatus.FORBIDDEN.value()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse notFound(NotFoundException ex) {
        return new ErrorResponse(ex, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ErrorResponse conflict(ConflictException ex) {
        return new ErrorResponse(ex, HttpStatus.CONFLICT.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse internalServerError(Exception ex) {
        ex.printStackTrace();
        return new ErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
