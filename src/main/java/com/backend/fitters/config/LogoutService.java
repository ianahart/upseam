package com.backend.fitters.config;

import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.token.TokenRepository;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LogoutService implements LogoutHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public LogoutService(JwtService jwtService, UserRepository userRepository, TokenRepository tokenRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String authHeader = request.getHeader("Authorization");
        String jwt;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        jwt = authHeader.substring(7);

        var storedToken = this.tokenRepository.findByToken(jwt).orElse(null);
        storedToken.setRevoked(true);
        storedToken.setExpired(true);
        this.tokenRepository.save(storedToken);
        SecurityContextHolder.clearContext();

    }
}
