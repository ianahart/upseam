package com.backend.fitters.bid.response;

public class DeleteBidResponse {
    private String message;

    public DeleteBidResponse() {

    }

    public DeleteBidResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
