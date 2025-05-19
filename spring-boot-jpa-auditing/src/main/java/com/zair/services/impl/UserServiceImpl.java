package com.zair.services.impl;

import com.zair.api.dtos.request.UserRequestDto;
import com.zair.api.dtos.response.UserResponseDto;
import com.zair.data.entities.UserEntity;
import com.zair.data.repositories.UserRepository;
import com.zair.services.UserService;
import com.zair.services.exceptions.ConflictException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserResponseDto create(UserRequestDto userRequestDto) {
        assertNotExist(userRequestDto.getUsername());

        userRequestDto.doDefault();
        UserEntity saved = userRepository.save(userRequestDto.toEntity());
        return UserResponseDto.toResponse(saved);
    }

    private void assertNotExist(String username) {
        if (findByUsername(username).isPresent()) {
            throw new ConflictException("Username already exists: " + username);
        }
    }
}
