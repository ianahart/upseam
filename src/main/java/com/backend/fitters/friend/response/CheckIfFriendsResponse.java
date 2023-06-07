package com.backend.fitters.friend.response;

public class CheckIfFriendsResponse {
    private boolean areFriends;
    private String message;

    public CheckIfFriendsResponse() {

    }

    public CheckIfFriendsResponse(boolean areFriends, String message) {
        this.areFriends = areFriends;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean getAreFriends() {
        return areFriends;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAreFriends(boolean areFriends) {
        this.areFriends = areFriends;
    }

}
