package com.backend.fitters.friend;

import java.util.List;

import com.backend.fitters.friend.request.RemoveFriendRequest;
import com.backend.fitters.friend.response.GetFriendsResponse;
import com.backend.fitters.friend.response.RemoveFriendResponse;
import com.backend.fitters.user.dto.GetFriendsDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/friends")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/unfriend")
    public ResponseEntity<RemoveFriendResponse> removeFriend(@RequestBody RemoveFriendRequest request) {
        this.friendService.removeFriend(request);
        return ResponseEntity.status(HttpStatus.OK).body(new RemoveFriendResponse("success"));
    }

    @GetMapping
    public ResponseEntity<GetFriendsResponse> getFriends(
            @RequestParam("userId") Long userId,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("direction") String direction) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetFriendsResponse(this.friendService.getFriends(userId, page, pageSize, direction),
                        "success"));
    }
}
