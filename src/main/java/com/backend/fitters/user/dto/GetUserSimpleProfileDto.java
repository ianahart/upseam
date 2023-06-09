package com.backend.fitters.user.dto;

public class GetUserSimpleProfileDto {
    private Long id;
    private String email;
    private String avatarUrl;
    private String address;
    private String country;
    private String zipCode;
    private String state;
    private String firstName;
    private String lastName;

    public GetUserSimpleProfileDto() {

    }

    public GetUserSimpleProfileDto(
            Long id,
            String email,
            String avatarUrl,
            String address,
            String country,
            String zipCode,
            String state,
            String firstName,
            String lastName) {
        this.id = id;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.address = address;
        this.country = country;
        this.zipCode = zipCode;
        this.state = state;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
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

    public String getZipCode() {
        return zipCode;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
