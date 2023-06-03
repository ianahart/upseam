package com.backend.fitters.friendship;

import com.backend.fitters.friendship.request.CreateFriendShipRequest;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserRepository;
import com.backend.fitters.user.UserService;
import com.backend.fitters.advice.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendShipService {

    private final FriendShipRepository friendShipRepository;
    private final UserService userService;

    @Autowired
    public FriendShipService(
            FriendShipRepository friendShipRepository,
            UserService userService) {
        this.friendShipRepository = friendShipRepository;
        this.userService = userService;
    }

    public void createFriendShip(CreateFriendShipRequest request) {

        if (this.friendShipRepository.checkIfFriendRequestSent(request.getRequestee(), request.getRequester())) {
            throw new BadRequestException("You have already sent this person a friend request");
        }

        User requester = this.userService.getUserById(request.getRequester());
        User requestee = this.userService.getUserById(request.getRequestee());

        this.friendShipRepository.save(new FriendShip(
                true,
                false,
                false,
                requester,
                requestee));
    }
}
