package com.backend.fitters.cloth.response;

public class CreateClothResponse {
    private String message;

    public CreateClothResponse() {

    }

    public CreateClothResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
