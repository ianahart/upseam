package com.backend.fitters.bid.request;

public class CreateBidRequest {
    private Long userId;
    private Long clothId;
    private String bid;

    public CreateBidRequest() {

    }

    public CreateBidRequest(Long userId, Long clothId, String bid) {
        this.userId = userId;
        this.clothId = clothId;
        this.bid = bid;
    }

    public String getBid() {
        return bid;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getClothId() {
        return clothId;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setClothId(Long clothId) {
        this.clothId = clothId;
    }
}
