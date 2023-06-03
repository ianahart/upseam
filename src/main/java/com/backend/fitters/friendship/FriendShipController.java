package com.backend.fitters.friendship;

import com.backend.fitters.friendship.request.CreateFriendShipRequest;
import com.backend.fitters.friendship.response.CreateFriendShipResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/friendships")
public class FriendShipController {

    private final FriendShipService friendShipService;

    @Autowired
    public FriendShipController(FriendShipService friendShipService) {
        this.friendShipService = friendShipService;
    }

    @PostMapping
    public ResponseEntity<CreateFriendShipResponse> createFriendShip(@RequestBody CreateFriendShipRequest request) {
        this.friendShipService.createFriendShip(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateFriendShipResponse("success"));
    }
}
