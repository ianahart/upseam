package com.backend.fitters.order.response;

public class CreateOrderResponse {
    private String message;

    public CreateOrderResponse() {

    }

    public CreateOrderResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
