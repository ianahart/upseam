package com.backend.fitters.payment.request;

import java.math.BigDecimal;

public class CreatePaymentRequest {
    private BigDecimal amount;
    private Long customerUserId;
    private Long billerUserId;
    private String email;

    public CreatePaymentRequest() {

    }

    public CreatePaymentRequest(
            BigDecimal amount,
            Long customerUserId,
            Long billerUserId,
            String email) {
        this.amount = amount;
        this.customerUserId = customerUserId;
        this.billerUserId = billerUserId;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getBillerUserId() {
        return billerUserId;
    }

    public Long getCustomerUserId() {
        return customerUserId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setBillerUserId(Long billerUserId) {
        this.billerUserId = billerUserId;
    }

    public void setCustomerUserId(Long customerUserId) {
        this.customerUserId = customerUserId;
    }
}
