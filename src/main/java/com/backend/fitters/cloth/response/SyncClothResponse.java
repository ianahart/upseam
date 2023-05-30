package com.backend.fitters.cloth.response;

import com.backend.fitters.cloth.dto.ClothDto;

public class SyncClothResponse {

    private ClothDto cloth;
    private String message;

    public SyncClothResponse() {

    }

    public SyncClothResponse(ClothDto cloth, String message) {
        this.cloth = cloth;
        this.message = message;
    }

    public ClothDto getCloth() {
        return cloth;
    }

    public String getMessage() {
        return message;
    }

    public void setCloth(ClothDto cloth) {
        this.cloth = cloth;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
