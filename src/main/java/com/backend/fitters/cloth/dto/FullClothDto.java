package com.backend.fitters.cloth.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public class FullClothDto {
    private Long id;
    private String clothUrl;
    private LocalDate dueDate;
    private String size;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String firstName;
    private String lastName;
    private String email;
    private Long userId;
    private Boolean closed;
    private Long closedId;

    public FullClothDto() {

    }

    public FullClothDto(
            Long id,
            String clothUrl,
            LocalDate dueDate,
            String size,
            String description,
            Timestamp createdAt,
            Timestamp updatedAt,
            String firstName,
            String lastName,
            String email,
            Long userId,
            Boolean closed,
            Long closedId) {
        this.id = id;
        this.clothUrl = clothUrl;
        this.dueDate = dueDate;
        this.size = size;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userId = userId;
        this.closed = closed;
        this.closedId = closedId;
    }

    public Long getId() {
        return id;
    }

    public Boolean getClosed() {
        return closed;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getClosedId() {
        return closedId;
    }

    public String getSize() {
        return size;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getClothUrl() {
        return clothUrl;
    }

    public String getLastName() {
        return lastName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setClothUrl(String clothUrl) {
        this.clothUrl = clothUrl;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setClosedId(Long closedId) {
        this.closedId = closedId;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
