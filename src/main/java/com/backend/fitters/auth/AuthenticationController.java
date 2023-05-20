package com.backend.fitters.auth;

import com.backend.fitters.auth.request.LoginRequest;
import com.backend.fitters.auth.request.RegisterRequest;
import com.backend.fitters.auth.response.LoginResponse;
import com.backend.fitters.auth.response.RegisterResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
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
}
