package com.backend.fitters.subscriber.request;

public class CreateSubscriberRequest {
    private String email;

    public CreateSubscriberRequest() {

    }

    public CreateSubscriberRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
