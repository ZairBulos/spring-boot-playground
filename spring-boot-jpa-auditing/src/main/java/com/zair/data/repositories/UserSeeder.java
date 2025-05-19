package com.zair.data.repositories;

import com.zair.data.entities.UserEntity;
import com.zair.data.enums.Role;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class UserSeeder {
    private final UserRepository userRepository;

    @Autowired
    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.seedDatabase();
    }

    private void seedDatabase() {
        log.warn("----- Initializing Users -----");
        UserEntity admin = UserEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .username("admin@example.com")
                .password(new BCryptPasswordEncoder().encode("12345678"))
                .role(Role.ADMIN)
                .deleted(false)
                .build();
        userRepository.save(admin);
    }
}
