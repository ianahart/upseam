package com.backend.fitters.profile.request;

import org.springframework.web.multipart.MultipartFile;

public class ProfilePhotoRequest {

    private MultipartFile file;
    private Long profileId;

    public ProfilePhotoRequest() {

    }

    public ProfilePhotoRequest(MultipartFile file, Long profileId) {
        this.file = file;
        this.profileId = profileId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
