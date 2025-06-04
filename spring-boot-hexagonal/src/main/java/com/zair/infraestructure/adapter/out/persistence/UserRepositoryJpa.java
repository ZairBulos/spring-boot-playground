package com.zair.infraestructure.adapter.out.persistence;

import com.zair.application.port.out.UserPersistencePort;
import com.zair.domain.model.User;
import com.zair.infraestructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJpa implements UserPersistencePort {
    private final SpringDataUserRepository repository;
    private final UserMapper mapper;

    @Override
    public List<User> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository
                .findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository
                .findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = mapper.toEntity(user);
        return mapper.toDomain(repository.save(userEntity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
