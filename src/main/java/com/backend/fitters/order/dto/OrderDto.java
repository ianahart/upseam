package com.backend.fitters.order.dto;

import java.time.LocalDate;

public class OrderDto {
    private Long id;
    private Long clothId;
    private String clothUrl;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private LocalDate dueDate;
    private Boolean complete;

    public OrderDto() {

    }

    public OrderDto(
            Long id,
            Long clothId,
            String clothUrl,
            String firstName,
            String lastName,
            String avatarUrl,
            LocalDate dueDate,
            Boolean complete) {

        this.id = id;
        this.clothId = clothId;
        this.clothUrl = clothUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
        this.dueDate = dueDate;
        this.complete = complete;
    }

    public Long getId() {
        return id;
    }

    public Boolean getComplete() {
        return complete;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getClothId() {
        return clothId;
    }

    public String getClothUrl() {
        return clothUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setClothId(Long clothId) {
        this.clothId = clothId;
    }

    public void setClothUrl(String clothUrl) {
        this.clothUrl = clothUrl;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
