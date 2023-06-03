package com.backend.fitters.friendship.response;

import java.util.List;

import com.backend.fitters.friendship.dto.GetFriendShipsDto;

import org.springframework.data.domain.Page;

public class GetFriendShipsResponse {
    private Page<GetFriendShipsDto> friendRequests;
    private String message;

    public GetFriendShipsResponse() {

    }

    public GetFriendShipsResponse(Page<GetFriendShipsDto> friendRequests, String message) {
        this.friendRequests = friendRequests;
        this.message = message;
    }

    public Page<GetFriendShipsDto> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(Page<GetFriendShipsDto> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
