package com.backend.fitters.auth.response;

import com.backend.fitters.auth.dto.UserDto;

public class LoginResponse {
    private String token;
    private UserDto userDto;

    public String getToken() {
        return token;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public LoginResponse(String token, UserDto userDto) {
        this.token = token;
        this.userDto = userDto;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
