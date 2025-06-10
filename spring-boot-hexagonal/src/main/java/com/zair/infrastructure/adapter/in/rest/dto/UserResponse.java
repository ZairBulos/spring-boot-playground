package com.zair.infrastructure.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zair.application.port.in.dto.UserDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(Long id, String name, String email) {

    public static UserResponse fromDto(UserDto applicationDto)  {
        return new UserResponse(
                applicationDto.id(),
                applicationDto.name(),
                applicationDto.email()
        );
    }
}
