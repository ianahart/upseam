package com.backend.fitters.cloth.request;

public class UpdateClothClosedRequest {
    private Long bidId;

    public UpdateClothClosedRequest() {

    }

    public UpdateClothClosedRequest(Long bidId) {
        this.bidId = bidId;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }
}
