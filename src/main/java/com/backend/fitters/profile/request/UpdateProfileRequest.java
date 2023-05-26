package com.backend.fitters.profile.request;

import jakarta.persistence.Column;

public class UpdateProfileRequest {
    private String specialities;
    private String country;
    private String state;
    private String address;
    @Column(name = "zip_code")
    private String zipCode;
    private String site;
    private String bio;
    private String pricing;

    public UpdateProfileRequest() {

    }

    public UpdateProfileRequest(String specialities,
            String country,
            String state,
            String address,
            String zipCode,
            String site,
            String bio,
            String pricing) {
        this.specialities = specialities;
        this.country = country;
        this.address = address;
        this.state = state;
        this.zipCode = zipCode;
        this.site = site;
        this.bio = bio;
        this.pricing = pricing;
    }

    public String getBio() {
        return bio;
    }

    public String getSite() {
        return site;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getPricing() {
        return pricing;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPricing(String pricing) {
        this.pricing = pricing;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

}
