package com.backend.fitters.invoice.request;

import java.math.BigDecimal;

public class CreateInvoiceRequest {
    private BigDecimal bid;
    private Long seamsterId;
    private Long orderId;

    public CreateInvoiceRequest() {

    }

    public CreateInvoiceRequest(
            BigDecimal bid,
            Long seamsterId,
            Long orderId) {
        this.bid = bid;
        this.seamsterId = seamsterId;
        this.orderId = orderId;

    }

    public BigDecimal getBid() {
        return bid;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getSeamsterId() {
        return seamsterId;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setSeamsterId(Long seamsterId) {
        this.seamsterId = seamsterId;
    }
}
