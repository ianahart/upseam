package com.backend.fitters.cloth.response;

import java.util.List;

import com.backend.fitters.cloth.dto.ClothesWithPaginationDto;

public class GetClothesResponse {
    private ClothesWithPaginationDto data;

    public GetClothesResponse() {

    }

    public GetClothesResponse(ClothesWithPaginationDto data) {
        this.data = data;
    }

    public ClothesWithPaginationDto getData() {
        return data;
    }

    public void setData(ClothesWithPaginationDto data) {
        this.data = data;
    }
}
