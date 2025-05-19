package com.zair.services;

import com.zair.api.dtos.request.LoginRequestDto;
import com.zair.api.dtos.response.TokenResponseDto;

public interface AuthService {
    TokenResponseDto login(LoginRequestDto loginRequestDto);
}
