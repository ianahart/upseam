package com.backend.fitters.cloth.response;

import com.backend.fitters.cloth.dto.FullClothDto;

public class FullClothResponse {
    private String message;
    private FullClothDto cloth;

    public FullClothResponse() {

    }

    public FullClothResponse(String message, FullClothDto cloth) {

        this.message = message;
        this.cloth = cloth;
    }

    public String getMessage() {
        return message;
    }

    public FullClothDto getCloth() {
        return cloth;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCloth(FullClothDto cloth) {
        this.cloth = cloth;
    }
}
