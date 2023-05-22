package com.backend.fitters.auth;

import java.util.List;
import java.util.Optional;

import com.backend.fitters.util.MyUtils;
import com.backend.fitters.advice.BadRequestException;
import com.backend.fitters.advice.ForbiddenException;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.auth.dto.UserDto;
import com.backend.fitters.auth.request.LoginRequest;
import com.backend.fitters.auth.request.RegisterRequest;
import com.backend.fitters.auth.response.LoginResponse;
import com.backend.fitters.auth.response.RegisterResponse;
import com.backend.fitters.config.JwtService;
import com.backend.fitters.config.RefreshTokenService;
import com.backend.fitters.refreshtoken.RefreshToken;
import com.backend.fitters.token.Token;
import com.backend.fitters.token.TokenRepository;
import com.backend.fitters.token.TokenType;
import com.backend.fitters.user.Role;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationService(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            TokenRepository tokenRepository,
            JwtService jwtService,
            RefreshTokenService refreshTokenService,
            AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponse register(RegisterRequest request) {
        User user = new User(
                MyUtils.capitalize(request.getFirstName()),
                MyUtils.capitalize(request.getLastName()),
                request.getEmail(),
                this.passwordEncoder.encode(request.getPassword()),
                request.getRole().equals("USER") ? Role.USER : Role.SEAMSTER);

        Optional<User> exists = this.userRepository.findByEmail(request.getEmail());

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }
        if (exists.isPresent()) {
            throw new BadRequestException("A user with that email already exists.");
        }
        this.userRepository.save(user);

        return new RegisterResponse("User created.");
    }

    public void saveTokenWithUser(String token, User user) {
        Token tokenToSave = new Token(token, TokenType.BEARER, false, false, user);
        this.tokenRepository.save(tokenToSave);

    }

    public void revokeAllUserTokens(User user) {
        List<Token> tokens = this.tokenRepository.findAllValidTokens(user.getId());

        if (tokens.isEmpty()) {
            return;
        }

        tokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });

        this.tokenRepository.saveAll(tokens);
    }

    public LoginResponse login(LoginRequest request) {

        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

        } catch (BadCredentialsException e) {
            throw new ForbiddenException("Credentials are invalid");
        }
        User user = this.userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found by email."));
        String jwtToken = this.jwtService.generateToken(user);

        this.revokeAllUserTokens(user);
        this.saveTokenWithUser(jwtToken, user);
        RefreshToken refreshToken = this.refreshTokenService.generateRefreshToken(user.getId());

        UserDto userDto = new UserDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getAbbreviation(),
                true);
        return new LoginResponse(jwtToken, refreshToken.getRefreshToken(), userDto);
    }
}
