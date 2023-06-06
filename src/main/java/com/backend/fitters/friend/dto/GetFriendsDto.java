package com.backend.fitters.friend.dto;

public class GetFriendsDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long friendId;
    private Long profileId;
    private String avatarUrl;

    public GetFriendsDto() {

    }

    public GetFriendsDto(
            Long id,
            String firstName,
            String lastName,
            Long friendId,
            Long profileId,
            String avatarUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.friendId = friendId;
        this.profileId = profileId;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getFriendId() {
        return friendId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
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

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
