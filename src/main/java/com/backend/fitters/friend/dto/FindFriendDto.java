package com.backend.fitters.friend.dto;

public class FindFriendDto {
    private Long friendObjectId;

    public FindFriendDto() {

    }

    public FindFriendDto(Long friendObjectId) {
        this.friendObjectId = friendObjectId;
    }

    public Long getFriendObjectId() {
        return friendObjectId;
    }

    public void setFriendObjectId(Long friendObjectId) {
        this.friendObjectId = friendObjectId;
    }
}
