package com.backend.fitters.profile.dto;

import com.backend.fitters.profile.Profile;

public class ProfileDto {
    private Profile profile;
    private String firstName;
    private String lastName;

    public ProfileDto() {

    }

    public ProfileDto(Profile profile, String firstName, String lastName) {
        this.profile = profile;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
