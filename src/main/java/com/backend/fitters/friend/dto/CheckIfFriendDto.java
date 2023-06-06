package com.backend.fitters.friend.dto;

public class CheckIfFriendDto {
    private Long friendId;

    public CheckIfFriendDto() {

    }

    public CheckIfFriendDto(Long friendId) {
        this.friendId = friendId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
}
