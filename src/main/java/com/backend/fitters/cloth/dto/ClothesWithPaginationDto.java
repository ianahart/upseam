package com.backend.fitters.cloth.dto;

import org.springframework.data.domain.Page;

public class ClothesWithPaginationDto {

    private Page<ClothesDto> clothes;
    private int page;
    private String direction;

    public ClothesWithPaginationDto() {

    }

    public ClothesWithPaginationDto(Page<ClothesDto> clothes, int page, String direction) {
        this.clothes = clothes;
        this.page = page;
        this.direction = direction;
    }

    public int getPage() {
        return page;
    }

    public String getDirection() {
        return direction;
    }

    public Page<ClothesDto> getClothes() {
        return clothes;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setClothes(Page<ClothesDto> clothes) {
        this.clothes = clothes;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
