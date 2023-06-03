package com.backend.fitters.friendship;

import com.backend.fitters.friendship.request.CreateFriendShipRequest;
import com.backend.fitters.friendship.request.UpdateFriendShipRequest;
import com.backend.fitters.friendship.response.CreateFriendShipResponse;
import com.backend.fitters.friendship.response.GetFriendShipsResponse;
import com.backend.fitters.friendship.response.UpdateFriendShipResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/friendships")
public class FriendShipController {

    private final FriendShipService friendShipService;

    @Autowired
    public FriendShipController(FriendShipService friendShipService) {
        this.friendShipService = friendShipService;
    }

    @GetMapping
    public ResponseEntity<GetFriendShipsResponse> getFriendRequests(
            @RequestParam("requesteeId") Long requesteeId,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new GetFriendShipsResponse(this.friendShipService.getFriendRequests(requesteeId, page, pageSize),
                        "success"));
    }

    @PostMapping
    public ResponseEntity<CreateFriendShipResponse> createFriendShip(@RequestBody CreateFriendShipRequest request) {
        this.friendShipService.createFriendShip(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateFriendShipResponse("success"));
    }

    @PatchMapping("/{friendShipId}")
    public ResponseEntity<UpdateFriendShipResponse> updateFriendShip(
            @PathVariable("friendShipId") Long friendShipId,
            @RequestBody UpdateFriendShipRequest request) {
        this.friendShipService.updateFriendShip(friendShipId, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new UpdateFriendShipResponse("success"));
    }
}
