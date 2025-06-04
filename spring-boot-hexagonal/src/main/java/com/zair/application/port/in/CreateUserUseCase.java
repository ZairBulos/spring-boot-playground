package com.zair.application.port.in;

import com.zair.domain.model.User;

public interface CreateUserUseCase {
    User create(User user);
}
