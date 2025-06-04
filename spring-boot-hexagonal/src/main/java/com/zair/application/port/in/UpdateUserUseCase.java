package com.zair.application.port.in;

import com.zair.domain.model.User;

public interface UpdateUserUseCase {
    User update(Long id, User user);
}
