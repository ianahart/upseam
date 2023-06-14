package com.backend.fitters.shipping.response;

public class RemoveShippingResponse {
    private String message;

    public RemoveShippingResponse() {

    }

    public RemoveShippingResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
