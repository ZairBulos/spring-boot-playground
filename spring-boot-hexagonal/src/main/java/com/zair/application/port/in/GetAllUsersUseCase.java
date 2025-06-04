package com.zair.application.port.in;

import com.zair.domain.model.User;

import java.util.List;

public interface GetAllUsersUseCase {
    List<User> getAll();
}
