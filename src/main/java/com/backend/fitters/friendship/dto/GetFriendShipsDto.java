package com.backend.fitters.friendship.dto;

public class GetFriendShipsDto {
    private Long id;
    private Long requesteeId;
    private Long requesterId;
    private String firstName;
    private String lastName;
    private Long profileId;
    private String avatarUrl;

    public GetFriendShipsDto() {

    }

    public GetFriendShipsDto(
            Long id,
            Long requesteeId,
            Long requesterId,
            String firstName,
            String lastName,
            Long profileId,
            String avatarUrl) {
        this.id = id;
        this.requesteeId = requesteeId;
        this.requesterId = requesterId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileId = profileId;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public Long getRequesteeId() {
        return requesteeId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getProfileId() {
        return profileId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRequesteeId(Long requesteeId) {
        this.requesteeId = requesteeId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

}
