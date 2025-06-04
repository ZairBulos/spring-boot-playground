package com.zair.application.service;

import com.zair.application.port.in.*;
import com.zair.application.port.out.UserPersistencePort;
import com.zair.domain.exception.UserAlreadyExistsException;
import com.zair.domain.exception.UserNotFoundException;
import com.zair.domain.model.User;

import java.util.List;

public class UserService implements
        GetAllUsersUseCase,
        GetUserByIdUseCase,
        CreateUserUseCase,
        UpdateUserUseCase,
        DeleteUserUseCase {
    private final UserPersistencePort userPersistencePort;

    public UserService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public List<User> getAll() {
        return userPersistencePort.findAll();
    }

    @Override
    public User getById(Long id) {
        return userPersistencePort
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
    }

    @Override
    public User create(User user) {
        assertNotExists(user.getEmail());
        return userPersistencePort.save(user);
    }

    @Override
    public User update(Long id, User user) {
        userPersistencePort
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
        user.setId(id);
        return userPersistencePort.save(user);
    }

    @Override
    public void delete(Long id) {
        userPersistencePort.delete(id);
    }

    private void assertNotExists(String email) {
        userPersistencePort
                .findByEmail(email)
                .ifPresent(user -> { throw new UserAlreadyExistsException("User already exists: " + email);});
    }
}
