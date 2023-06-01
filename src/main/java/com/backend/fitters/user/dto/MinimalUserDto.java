package com.backend.fitters.user.dto;

import com.backend.fitters.user.Role;

public class MinimalUserDto {
    private String firstName;
    private String lastName;
    private Role role;
    private Long profileUserId;

    public MinimalUserDto() {

    }

    public MinimalUserDto(String firstName, String lastName, Role role, Long profileUserId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.profileUserId = profileUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getProfileUserId() {
        return profileUserId;
    }

    public Role getRole() {
        return role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setProfileUserId(Long profileUserId) {
        this.profileUserId = profileUserId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
