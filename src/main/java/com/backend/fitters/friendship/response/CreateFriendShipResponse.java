package com.backend.fitters.friendship.response;

public class CreateFriendShipResponse {
    private String message;

    public CreateFriendShipResponse() {

    }

    public CreateFriendShipResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
