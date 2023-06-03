package com.backend.fitters.friendship.request;

public class UpdateFriendShipRequest {

    private Long requesteeId;
    private Long requesterId;
    private String action;

    public UpdateFriendShipRequest() {

    }

    public UpdateFriendShipRequest(
            Long requesteeId,
            Long requesterId,
            String action) {

        this.requesteeId = requesteeId;
        this.requesterId = requesterId;
        this.action = action;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public Long getRequesteeId() {
        return requesteeId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setRequesteeId(Long requesteeId) {
        this.requesteeId = requesteeId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }
}
