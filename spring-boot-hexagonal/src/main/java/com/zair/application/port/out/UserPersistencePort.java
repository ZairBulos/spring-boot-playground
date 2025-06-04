package com.zair.application.port.out;

import com.zair.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    User save(User user);
    void delete(Long id);
}
