package com.backend.fitters.user.response;

import com.backend.fitters.user.dto.GetUserSimpleProfileDto;

public class GetUserSimpleProfileResponse {
    private GetUserSimpleProfileDto userProfile;
    private String message;

    public GetUserSimpleProfileResponse() {

    }

    public GetUserSimpleProfileResponse(
            GetUserSimpleProfileDto userProfile,
            String message) {

        this.userProfile = userProfile;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public GetUserSimpleProfileDto getUserProfile() {
        return userProfile;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserProfile(GetUserSimpleProfileDto userProfile) {
        this.userProfile = userProfile;
    }
}
