package com.backend.fitters.user.dto;

public class GetFriendsDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private Long profileId;
    private String avatarUrl;

    public GetFriendsDto() {

    }

    public GetFriendsDto(
            Long userId,
            String firstName,
            String lastName,
            Long profileId,
            String avatarUrl) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileId = profileId;
        this.avatarUrl = avatarUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
