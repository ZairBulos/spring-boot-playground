package com.zair.config;

import com.zair.data.enums.Role;
import com.zair.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwtService.extractToken(request.getHeader(AUTHORIZATION));
        Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

        if (!token.isEmpty() && existingAuth == null) {
            String id = jwtService.idFromToken(token);
            String username = jwtService.usernameFromToken(token);
            String role = jwtService.roleFromToken(token);

            setAuthentication(id, username, role);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String id, String username, String role) {
        if (username != null && role != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority(Role.PREFIX + role);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, List.of(authority)
            );
            authentication.setDetails(id);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
