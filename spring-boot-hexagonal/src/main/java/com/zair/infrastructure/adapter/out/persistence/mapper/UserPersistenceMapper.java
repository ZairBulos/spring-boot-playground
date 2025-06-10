package com.zair.infrastructure.adapter.out.persistence.mapper;

import com.zair.domain.model.User;
import com.zair.infrastructure.adapter.out.persistence.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;

        return new User(entity.getId(), entity.getName(), entity.getEmail());
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        return entity;
    }
}
