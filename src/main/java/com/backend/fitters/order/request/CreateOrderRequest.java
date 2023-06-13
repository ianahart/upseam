package com.backend.fitters.order.request;

public class CreateOrderRequest {
    private Long userId;
    private Long clothId;
    private Long bidUserId;

    public CreateOrderRequest() {

    }

    public CreateOrderRequest(
            Long userId,
            Long clothId,
            Long bidUserId) {

        this.userId = userId;
        this.clothId = clothId;
        this.bidUserId = bidUserId;
    }

    public Long getUserId() {
        return userId;
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

    public void setBidUserId(Long bidUserId) {
        this.bidUserId = bidUserId;
    }
}
