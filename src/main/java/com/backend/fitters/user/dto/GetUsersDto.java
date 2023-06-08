package com.backend.fitters.user.dto;

import com.backend.fitters.user.Role;

public class GetUsersDto {
    private Role role;
    private Long id;
    private String firstName;
    private String lastName;
    private Long profileId;
    private String avatarUrl;

    public GetUsersDto() {

    }

    public GetUsersDto(
            Role role,
            Long id,
            String firstName,
            String lastName,
            Long profileId,
            String avatarUrl) {
        this.role = role;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileId = profileId;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
