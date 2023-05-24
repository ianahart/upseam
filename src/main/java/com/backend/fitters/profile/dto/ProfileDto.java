package com.backend.fitters.profile.dto;

import com.backend.fitters.user.dto.MinimalUserDto;
import com.backend.fitters.profile.Profile;

public class ProfileDto {
    private ProfileFieldsDto profile;
    private MinimalUserDto user;

    public ProfileDto() {

    }

    public ProfileDto(ProfileFieldsDto profile, MinimalUserDto user) {
        this.profile = profile;
        this.user = user;
    }

    public MinimalUserDto getUser() {
        return user;
    }

    public ProfileFieldsDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileFieldsDto profile) {
        this.profile = profile;
    }

    public void setUser(MinimalUserDto user) {
        this.user = user;
    }

}
