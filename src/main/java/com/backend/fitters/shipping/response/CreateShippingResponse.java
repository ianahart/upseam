package com.backend.fitters.shipping.response;

public class CreateShippingResponse {
    private String message;

    public CreateShippingResponse() {

    }

    public CreateShippingResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
