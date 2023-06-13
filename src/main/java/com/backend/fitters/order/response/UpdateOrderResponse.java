package com.backend.fitters.order.response;

public class UpdateOrderResponse {

    private String message;

    public UpdateOrderResponse() {

    }

    public UpdateOrderResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
