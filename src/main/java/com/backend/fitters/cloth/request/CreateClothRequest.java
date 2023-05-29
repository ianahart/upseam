package com.backend.fitters.cloth.request;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class CreateClothRequest {
    private MultipartFile file;
    private String dueDate;
    private String description;
    private String size;
    private Long userId;

    public CreateClothRequest() {

    }

    public CreateClothRequest(
            MultipartFile file,
            String dueDate,
            String description,
            String size,
            Long userId) {
        this.file = file;
        this.dueDate = dueDate;
        this.description = description;
        this.size = size;
        this.userId = userId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getSize() {
        return size;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
