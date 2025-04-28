package com.zair.api.http_errors;

import com.zair.services.exceptions.ConflictException;
import com.zair.services.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    @ResponseBody
    public ErrorResponse conflict(ConflictException ex) {
        return new ErrorResponse(ex, HttpStatus.CONFLICT.value());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorResponse notFound(NotFoundException ex) {
        return new ErrorResponse(ex, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResponse exception(Exception ex) {
        ex.printStackTrace();
        return new ErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
