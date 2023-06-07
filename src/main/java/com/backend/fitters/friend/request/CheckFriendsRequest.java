package com.backend.fitters.friend.request;

public class CheckFriendsRequest {
    private Long currentUserId;
    private Long userId;

    public CheckFriendsRequest() {

    }

    public CheckFriendsRequest(Long currentUserId, Long userId) {
        this.currentUserId = currentUserId;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

}
