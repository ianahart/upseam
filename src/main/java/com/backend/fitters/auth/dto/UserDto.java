package com.backend.fitters.auth.dto;

import com.backend.fitters.user.Role;

public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private String abbreviation;
    private Long profileId;
    private boolean isLoggedIn;
    private String avatarUrl;

    public UserDto(Long id, String email, String firstName, String lastName, Role role, String abbreviation,
            Long profileId,
            boolean isLoggedIn, String avatarUrl) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.abbreviation = abbreviation;
        this.profileId = profileId;
        this.isLoggedIn = isLoggedIn;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public Long getProfileId() {
        return profileId;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

}
