package com.zair.services;

public interface JwtService {
    String extractToken(String bearer);
    String generateToken(String id, String username, String role);
    String idFromToken(String token);
    String usernameFromToken(String token);
    String roleFromToken(String token);
}
