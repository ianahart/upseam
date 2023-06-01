package com.backend.fitters.bid.response;

public class CreateBidResponse {
    private String message;

    public CreateBidResponse() {

    }

    public CreateBidResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
