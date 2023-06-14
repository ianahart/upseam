package com.backend.fitters.shipping.request;

public class CreateShippingRequest {

    private Long userId;
    private String zipCode;
    private String state;
    private String country;
    private String address;
    private String firstName;
    private String lastName;
    private String shippingType;
    private String shippingValue;

    public CreateShippingRequest() {

    }

    public CreateShippingRequest(
            Long userId,
            String zipCode,
            String state,
            String country,
            String address,
            String firstName,
            String lastName,
            String shippingType,
            String shippingValue) {

        this.userId = userId;
        this.zipCode = zipCode;
        this.state = state;
        this.country = country;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shippingType = shippingType;
        this.shippingValue = shippingValue;
    }

    public String getState() {
        return state;
    }

    public Long getUserId() {
        return userId;
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

    public String getShippingType() {
        return shippingType;
    }

    public String getShippingValue() {
        return shippingValue;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public void setShippingValue(String shippingValue) {
        this.shippingValue = shippingValue;
    }
}
