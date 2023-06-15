package com.backend.fitters.order.request;

import java.math.BigDecimal;

public class CreateOrderRequest {
    private Long userId;
    private Long clothId;
    private Long bidUserId;
    private BigDecimal bid;

    public CreateOrderRequest() {

    }

    public CreateOrderRequest(
            Long userId,
            Long clothId,
            Long bidUserId,
            BigDecimal bid) {

        this.userId = userId;
        this.clothId = clothId;
        this.bidUserId = bidUserId;
        this.bid = bid;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public Long getBidUserId() {
        return bidUserId;
    }

    public Long getClothId() {
        return clothId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setClothId(Long clothId) {
        this.clothId = clothId;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public void setBidUserId(Long bidUserId) {
        this.bidUserId = bidUserId;
    }
}
