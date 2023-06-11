package com.backend.fitters.subscriber.response;

public class CreateSubscriberResponse {
    private String message;

    public CreateSubscriberResponse() {

    }

    public CreateSubscriberResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
