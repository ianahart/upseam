package com.backend.fitters.user;

import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.auth.dto.UserDto;
import com.backend.fitters.user.request.UpdateUserRequest;
import com.backend.fitters.user.response.GetUserSimpleProfileResponse;
import com.backend.fitters.user.response.SearchResponse;
import com.backend.fitters.user.response.UpdateUserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/search")
    public ResponseEntity<SearchResponse> searchUsers(@RequestParam("term") String term,
            @RequestParam("page") int page) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new SearchResponse(this.userService.searchUsers(term, page), "success"));
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

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserSimpleProfileResponse> getUser(@PathVariable("userId") Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new GetUserSimpleProfileResponse(this.userService.getUserSimpleProfile(userId), "success"));

    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable("userId") Long userId,
            @RequestBody UpdateUserRequest request) {

        this.userService.updateUser(userId, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new UpdateUserResponse("success"));
    }

}
