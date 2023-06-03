package com.backend.fitters.friendship;

import com.backend.fitters.friendship.request.CreateFriendShipRequest;
import com.backend.fitters.friendship.request.UpdateFriendShipRequest;
import com.backend.fitters.user.User;
import com.backend.fitters.friendship.dto.GetFriendShipsDto;
import com.backend.fitters.user.UserService;
import com.backend.fitters.util.MyUtils;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.advice.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<GetFriendShipsDto> getFriendRequests(Long requesteeId, int page, int pageSize) {

        int currentPage = MyUtils.paginate(page, "next");

        Pageable paging = PageRequest.of(currentPage, pageSize, Sort.by("id"));

        return this.friendShipRepository.getFriendRequests(requesteeId, paging);
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

    public void updateFriendShip(Long friendShipId, UpdateFriendShipRequest request) {
        FriendShip fs = this.friendShipRepository.findById(friendShipId)
                .orElseThrow(() -> new NotFoundException("Friendship not found"));
        if (request.getAction().equals("accepted")) {
            fs.setAccepted(true);
        }

        if (request.getAction().equals("declined")) {
            fs.setDeclined(true);
        }

        fs.setPending(false);

        this.friendShipRepository.save(fs);
    }
}
