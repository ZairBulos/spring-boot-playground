package com.zair.infrastructure.adapter.in.rest.mapper;

import com.zair.application.port.in.dto.CreateUserCommand;
import com.zair.application.port.in.dto.UserDto;
import com.zair.infrastructure.adapter.in.rest.dto.CreateUserRequest;
import com.zair.infrastructure.adapter.in.rest.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserRestMapper {

    public CreateUserCommand toCommand(CreateUserRequest request) {
        if (request == null) return null;
        return new CreateUserCommand(request.name(), request.email());
    }

    public UserResponse toResponse(UserDto dto) {
        if (dto == null) return null;
        return UserResponse.fromDto(dto);
    }
}
