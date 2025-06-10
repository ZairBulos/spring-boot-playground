package com.zair.application.port.in;

import com.zair.application.port.in.dto.CreateUserCommand;
import com.zair.application.port.in.dto.UserDto;

import java.util.List;

public interface UserServicePort {
    List<UserDto> getAll();
    UserDto getById(Long id);
    UserDto create(CreateUserCommand command);
    UserDto update(Long id, CreateUserCommand command);
    void delete(Long id);
}
