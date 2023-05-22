package com.backend.fitters.user;

import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.auth.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/users")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sync")
    public ResponseEntity<UserDto> syncUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            throw new NotFoundException("token not in header");
        }

        String token = authHeader.substring(7);

        return ResponseEntity.status(200).body(this.userService.getUserByToken(token));

    }
}
