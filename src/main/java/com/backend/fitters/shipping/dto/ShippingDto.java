package com.backend.fitters.shipping.dto;

public class ShippingDto {

    private Long id;
    private Long userId;
    private String zipCode;
    private String state;
    private String country;
    private String address;
    private String firstName;
    private String lastName;
    private String shippingType;
    private String shippingValue;

    public ShippingDto() {

    }

    public ShippingDto(
            Long id,
            Long userId,
            String zipCode,
            String state,
            String country,
            String address,
            String firstName,
            String lastName,
            String shippingType,
            String shippingValue) {
        this.id = id;
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

    public Long getId() {
        return id;
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

    public String getFirstName() {
        return firstName;
    }

    public String getShippingType() {
        return shippingType;
    }

    public String getLastName() {
        return lastName;
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

    public void setId(Long id) {
        this.id = id;
    }
}
