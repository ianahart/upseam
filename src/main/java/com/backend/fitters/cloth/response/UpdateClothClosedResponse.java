package com.backend.fitters.cloth.response;

public class UpdateClothClosedResponse {
    private String message;

    public UpdateClothClosedResponse() {

    }

    public UpdateClothClosedResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
