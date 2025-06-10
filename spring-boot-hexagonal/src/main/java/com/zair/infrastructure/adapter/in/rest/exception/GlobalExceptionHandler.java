package com.zair.infrastructure.adapter.in.rest.exception;

import com.zair.domain.exception.UserAlreadyExistsException;
import com.zair.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleNotFound(UserNotFoundException ex) {
        return new ErrorResponse(
                ex.getClass().getSimpleName(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse badRequest(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return new ErrorResponse(
                ex.getClass().getSimpleName(),
                HttpStatus.BAD_REQUEST.value(),
                "The request contains invalid data.",
                details
        );
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ErrorResponse handleConflict(UserAlreadyExistsException ex) {
        return new ErrorResponse(
                ex.getClass().getSimpleName(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                null
        );
    }
}
