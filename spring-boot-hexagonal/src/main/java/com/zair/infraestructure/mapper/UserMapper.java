package com.zair.infraestructure.mapper;

import com.zair.domain.model.User;
import com.zair.infraestructure.adapter.in.rest.dtos.UserRequestDto;
import com.zair.infraestructure.adapter.in.rest.dtos.UserResponseDto;
import com.zair.infraestructure.adapter.out.persistence.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserRequestDto userRequestDto) {
        return new User(null, userRequestDto.name(), userRequestDto.email());
    }

    public User toDomain(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }

    public UserEntity toEntity(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getEmail());
    }

    public UserResponseDto toResponse(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }
}
