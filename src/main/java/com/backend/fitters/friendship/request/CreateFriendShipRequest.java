package com.backend.fitters.friendship.request;

public class CreateFriendShipRequest {
    private Long requestee;
    private Long requester;

    public CreateFriendShipRequest() {

    }

    public CreateFriendShipRequest(Long requestee, Long requester) {
        this.requestee = requestee;
        this.requester = requester;
    }

    public Long getRequestee() {
        return requestee;
    }

    public Long getRequester() {
        return requester;
    }

    public void setRequestee(Long requestee) {
        this.requestee = requestee;
    }

    public void setRequester(Long requester) {
        this.requester = requester;
    }
}
