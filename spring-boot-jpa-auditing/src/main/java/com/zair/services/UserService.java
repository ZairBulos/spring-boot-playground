package com.zair.services;

import com.zair.api.dtos.request.UserRequestDto;
import com.zair.api.dtos.response.UserResponseDto;
import com.zair.data.entities.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByUsername(String username);
    UserResponseDto create(UserRequestDto userRequestDto);
}
