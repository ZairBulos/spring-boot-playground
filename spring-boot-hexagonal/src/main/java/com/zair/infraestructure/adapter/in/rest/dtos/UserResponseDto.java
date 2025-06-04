package com.zair.infraestructure.adapter.in.rest.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDto(
        Long id,
        String name,
        String email
) {
}
