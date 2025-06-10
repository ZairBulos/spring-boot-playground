package com.zair.infrastructure.adapter.in.rest.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        String error,
        Integer code,
        String message,
        List<String> details
) {
}
