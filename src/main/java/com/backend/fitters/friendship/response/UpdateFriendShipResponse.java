package com.backend.fitters.friendship.response;

public class UpdateFriendShipResponse {
    private String message;

    public UpdateFriendShipResponse() {

    }

    public UpdateFriendShipResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
