package com.backend.fitters.friend.response;

import com.backend.fitters.friend.dto.FriendsPaginationDto;

public class GetFriendsResponse {
    private FriendsPaginationDto data;
    private String message;

    public GetFriendsResponse() {

    }

    public GetFriendsResponse(FriendsPaginationDto data, String message) {
        this.data = data;
        this.message = message;
    }

    public FriendsPaginationDto getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setData(FriendsPaginationDto data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
