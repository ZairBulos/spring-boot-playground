package com.zair.application.port.in;

import com.zair.domain.model.User;

public interface GetUserByIdUseCase {
    User getById(Long id);
}
