package com.backend.fitters.profile.response;

import com.backend.fitters.profile.dto.ProfileDto;

public class ProfileResponse {
    private ProfileDto data;
    private String message;

    public ProfileResponse() {

    }

    public ProfileResponse(ProfileDto data, String message) {
        this.data = data;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ProfileDto getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(ProfileDto data) {
        this.data = data;
    }
}
