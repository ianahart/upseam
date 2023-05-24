package com.backend.fitters.profile.response;

public class ProfilePhotoResponse {
    private String url;
    private String fileName;

    public ProfilePhotoResponse() {

    }

    public ProfilePhotoResponse(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
