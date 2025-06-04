package com.zair.infraestructure.config;

import com.zair.application.port.in.*;
import com.zair.application.port.out.UserPersistencePort;
import com.zair.application.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfig {

    @Bean
    public UserService userService(UserPersistencePort port) {
        return new UserService(port);
    }

    @Bean
    public GetAllUsersUseCase getAllUsersUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserService userService) {
        return userService;
    }
}
