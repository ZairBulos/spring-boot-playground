package com.zair.application.port.in.dto;

import com.zair.domain.model.User;

public record UserDto(Long id, String name, String email) {

    public static UserDto fromDomain(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
