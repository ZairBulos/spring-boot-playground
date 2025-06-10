package com.zair.application.service;

import com.zair.application.port.in.*;
import com.zair.application.port.in.dto.CreateUserCommand;
import com.zair.application.port.in.dto.UserDto;
import com.zair.application.port.out.UserPersistencePort;
import com.zair.domain.exception.UserAlreadyExistsException;
import com.zair.domain.exception.UserNotFoundException;
import com.zair.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserServicePort {
    private final UserPersistencePort persistence;

    public UserService(UserPersistencePort persistence) {
        this.persistence = persistence;
    }

    @Override
    public List<UserDto> getAll() {
        return persistence.findAll()
                .stream()
                .map(UserDto::fromDomain)
                .toList();
    }

    @Override
    public UserDto getById(Long id) {
        return persistence
                .findById(id)
                .map(UserDto::fromDomain)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
    }

    @Override
    @Transactional
    public UserDto create(CreateUserCommand command) {
        assertNotExists(command.email());
        User toSave = new User(null, command.name(), command.email());
        return UserDto.fromDomain(persistence.save(toSave));
    }

    @Override
    @Transactional
    public UserDto update(Long id, CreateUserCommand command) {
        persistence
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
        User toUpdate = new User(id, command.name(), command.email());
        return UserDto.fromDomain(persistence.save(toUpdate));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        persistence.delete(id);
    }

    private void assertNotExists(String email) {
        persistence
                .findByEmail(email)
                .ifPresent(user -> { throw new UserAlreadyExistsException("User already exists: " + email);});
    }
}
