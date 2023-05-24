package com.backend.fitters.auth;

import java.io.IOException;

import com.backend.fitters.auth.request.LoginRequest;
import com.backend.fitters.auth.request.RefreshTokenRequest;
import com.backend.fitters.auth.request.RegisterRequest;
import com.backend.fitters.auth.response.LoginResponse;
import com.backend.fitters.auth.response.RefreshTokenResponse;
import com.backend.fitters.auth.response.PasswordResetResponse;
import com.backend.fitters.auth.request.PasswordResetRequest;
import com.backend.fitters.auth.response.RegisterResponse;
import com.backend.fitters.config.JwtService;
import com.backend.fitters.config.RefreshTokenService;
import com.backend.fitters.refreshtoken.RefreshToken;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserService;
import com.backend.fitters.auth.request.EmailRequest;
import com.backend.fitters.auth.response.EmailResponse;
import com.backend.fitters.email.EmailService;
import com.backend.fitters.passwordreset.PasswordResetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final PasswordResetService passwordResetService;
    private final JwtService jwtService;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final EmailService emailService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
            JwtService jwtService, RefreshTokenService refreshTokenService,
            UserService userService,
            EmailService emailService,
            PasswordResetService passwordResetService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
        this.emailService = emailService;
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.authenticationService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request) {
        RefreshToken refreshToken = this.refreshTokenService.verifyRefreshToken(request.getRefreshToken());

        this.authenticationService.revokeAllUserTokens(refreshToken.getUser());
        String token = this.jwtService.generateToken(refreshToken.getUser());
        this.authenticationService.saveTokenWithUser(token, refreshToken.getUser());

        return ResponseEntity.status(200).body(
                new RefreshTokenResponse(token, refreshToken.getRefreshToken()));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest request)
            throws IOException, TemplateException, MessagingException {
        User user = this.userService.getUserByEmail(request.getEmail());

        this.passwordResetService.deleteUserPasswordResetsById(user.getId());
        return ResponseEntity
                .status(200)
                .body(this.emailService.sendSimpleMail(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<PasswordResetResponse> resetPassword(
            @RequestBody PasswordResetRequest request) {

        this.passwordResetService
                .isResetTokenValid(request.getToken());

        this.userService.resetUserPassword(request);

        this.passwordResetService.deleteUserPasswordResetsById(request.getId());
        return ResponseEntity.status(200).body(
                new PasswordResetResponse("Password has been reset."));
    }
}
