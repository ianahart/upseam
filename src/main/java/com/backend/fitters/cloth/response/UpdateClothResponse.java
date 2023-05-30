package com.backend.fitters.cloth.response;

public class UpdateClothResponse {
    private String message;

    public UpdateClothResponse() {

    }

    public UpdateClothResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
