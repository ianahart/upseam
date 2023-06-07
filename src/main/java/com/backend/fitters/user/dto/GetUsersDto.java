package com.backend.fitters.user.dto;

public class GetUsersDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long profileId;
    private String avatarUrl;

    public GetUsersDto() {

    }

    public GetUsersDto(Long id,
            String firstName,
            String lastName,
            Long profileId,
            String avatarUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileId = profileId;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
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

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
