package com.backend.fitters.cloth.dto;

import java.time.LocalDate;

public class ClothDto {
    private String clothUrl;
    private LocalDate dueDate;
    private Long id;
    private String description;
    private String size;
    private Long userId;

    public ClothDto() {

    }

    public ClothDto(
            String clothUrl,
            LocalDate dueDate,
            Long id,
            String description,
            String size,
            Long userId

    ) {

        this.clothUrl = clothUrl;
        this.dueDate = dueDate;
        this.id = id;
        this.description = description;
        this.size = size;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getClothUrl() {
        return clothUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setClothUrl(String clothUrl) {
        this.clothUrl = clothUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
