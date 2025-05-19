package com.zair.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zair.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtServiceImpl implements JwtService {
    private static final String BEARER = "Bearer ";
    private static final int PARTIES = 3;
    private static final String ID_CLAIM = "id";
    private static final String USER_CLAIM = "user";
    private static final String ROLE_CLAIM = "role";

    private final String SECRET;
    private final String ISSUER;
    private final int EXPIRE;

    @Autowired
    public JwtServiceImpl(@Value("${jwt.secret}") String SECRET,
                          @Value("${jwt.issuer}") String ISSUER,
                          @Value("${jwt.expire}") int EXPIRE) {
        this.SECRET = SECRET;
        this.ISSUER = ISSUER;
        this.EXPIRE = EXPIRE;
    }

    @Override
    public String extractToken(String bearer) {
        if (bearer != null && bearer.startsWith(BEARER) && PARTIES == bearer.split("\\.").length) {
            return bearer.substring(BEARER.length());
        }

        return "";
    }

    @Override
    public String generateToken(String id, String username, String role) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withNotBefore(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE * 1000L))
                .withClaim(ID_CLAIM, id)
                .withClaim(USER_CLAIM, username)
                .withClaim(ROLE_CLAIM, role)
                .sign(Algorithm.HMAC256(SECRET));
    }

    @Override
    public String idFromToken(String token) {
        return this.getClaim(token, ID_CLAIM);
    }

    @Override
    public String usernameFromToken(String token) {
        return this.getClaim(token, USER_CLAIM);
    }

    @Override
    public String roleFromToken(String token) {
        return this.getClaim(token, ROLE_CLAIM);
    }

    private String getClaim(String token, String claim) {
        return this.verify(token)
                .map(jwt -> jwt.getClaim(claim).asString())
                .orElse(null);
    }

    private Optional<DecodedJWT> verify(String token) {
        try {
            return Optional.of(
                    JWT.require(Algorithm.HMAC256(SECRET))
                            .withIssuer(ISSUER)
                            .build()
                            .verify(token)
            );
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
