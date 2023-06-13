package com.backend.fitters.order.request;

public class UpdateOrderRequest {
    private Boolean complete;

    public UpdateOrderRequest() {

    }

    public UpdateOrderRequest(Boolean complete) {
        this.complete = complete;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
