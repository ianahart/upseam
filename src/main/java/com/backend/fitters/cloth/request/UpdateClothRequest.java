package com.backend.fitters.cloth.request;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class UpdateClothRequest {
    private MultipartFile file;
    private String dueDate;
    private String description;
    private String size;
    private String userId;

    public UpdateClothRequest() {

    }

    public UpdateClothRequest(
            MultipartFile file,
            String dueDate,
            String description,
            String size,
            String userId) {
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

    public String getUserId() {
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

    public void setUserId(String userId) {
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
