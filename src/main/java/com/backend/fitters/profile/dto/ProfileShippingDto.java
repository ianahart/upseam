package com.backend.fitters.profile.dto;

public class ProfileShippingDto {
    private String zipCode;
    private String country;
    private String address;
    private String state;
    private String firstName;
    private String lastName;

    public ProfileShippingDto() {

    }

    public ProfileShippingDto(
            String zipCode,
            String country,
            String address,
            String state,
            String firstName,
            String lastName) {
        this.zipCode = zipCode;
        this.country = country;
        this.address = address;
        this.state = state;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
