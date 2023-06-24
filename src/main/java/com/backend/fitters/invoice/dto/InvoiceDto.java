package com.backend.fitters.invoice.dto;

import java.math.BigDecimal;

public class InvoiceDto {
    private Long invoiceId;
    private String avatarUrl;
    private String firstName;
    private String lastName;
    private BigDecimal bid;
    private String clothUrl;
    private Boolean paid;
    private Long userId;
    private Long clothId;

    public InvoiceDto() {

    }

    public InvoiceDto(
            Long invoiceId,
            String avatarUrl,
            String firstName,
            String lastName,
            BigDecimal bid,
            String clothUrl,
            Boolean paid,
            Long userId,
            Long clothId) {
        this.invoiceId = invoiceId;
        this.avatarUrl = avatarUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bid = bid;
        this.clothUrl = clothUrl;
        this.paid = paid;
        this.userId = userId;
        this.clothId = clothId;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public Long getClothId() {
        return clothId;
    }

    public Boolean getPaid() {
        return paid;
    }

    public Long getUserId() {
        return userId;
    }

    public String getClothUrl() {
        return clothUrl;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setClothId(Long clothId) {
        this.clothId = clothId;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public void setClothUrl(String clothUrl) {
        this.clothUrl = clothUrl;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
