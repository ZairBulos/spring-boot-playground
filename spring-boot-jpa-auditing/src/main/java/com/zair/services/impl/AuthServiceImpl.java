package com.zair.services.impl;

import com.zair.api.dtos.request.LoginRequestDto;
import com.zair.api.dtos.response.TokenResponseDto;
import com.zair.data.entities.UserEntity;
import com.zair.services.AuthService;
import com.zair.services.JwtService;
import com.zair.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponseDto login(LoginRequestDto loginRequestDto) {
        UserEntity user = userService
                .findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Incorrect username or password."));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect username or password.");
        }

        return generateToken(user.getId(), user.getUsername(), user.getRole().name());
    }

    private TokenResponseDto generateToken(String id, String username, String role) {
        String accessToken = jwtService.generateToken(id, username, role);
        return new TokenResponseDto(accessToken);
    }
}
