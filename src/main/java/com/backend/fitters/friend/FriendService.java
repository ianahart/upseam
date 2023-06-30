package com.backend.fitters.friend;

import com.backend.fitters.user.UserService;

import java.util.List;

import com.backend.fitters.user.dto.GetFriendsDto;
import com.backend.fitters.chat.ChatMessage;
import com.backend.fitters.chat.ChatRepository;
import com.backend.fitters.friend.dto.CheckIfFriendDto;
import com.backend.fitters.friend.dto.FindFriendDto;
import com.backend.fitters.friend.dto.FriendsPaginationDto;
import com.backend.fitters.friend.request.CheckFriendsRequest;
import com.backend.fitters.friend.request.RemoveFriendRequest;
import com.backend.fitters.friendship.FriendShip;
import com.backend.fitters.friendship.FriendShipRepository;
import com.backend.fitters.util.MyUtils;
import com.backend.fitters.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final FriendShipRepository friendShipRepository;
    private final UserService userService;
    private final ChatRepository chatRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository, UserService userService,
            FriendShipRepository friendShipRepository,
            ChatRepository chatRepository) {
        this.friendRepository = friendRepository;
        this.userService = userService;
        this.friendShipRepository = friendShipRepository;
        this.chatRepository = chatRepository;
    }

    public boolean checkIfFriends(CheckFriendsRequest request) {
        return this.friendRepository.checkIfFriends(request.getCurrentUserId(), request.getUserId());

    }

    @Transactional
    public void removeFriend(RemoveFriendRequest request) {
        FindFriendDto userOne = this.friendRepository.findFriend(request.getUserId(), request.getFriendId());
        FindFriendDto userTwo = this.friendRepository.findFriend(request.getFriendId(), request.getUserId());

        List<Long> ids = this.chatRepository.getMessagesToDelete(request.getUserId(),
                request.getFriendId()).stream().map(v -> v.getChatId()).toList();

        if (ids.size() > 0) {
            this.chatRepository.deleteChatMessagesWithIds(ids);
        }

        if (userOne != null) {
            this.friendRepository.deleteById(userOne.getFriendObjectId());
        }
        if (userTwo != null) {

            this.friendRepository.deleteById(userTwo.getFriendObjectId());
        }

        FriendShip fsOne = this.friendShipRepository.findFriendShip(request.getUserId(),
                request.getFriendId());
        FriendShip fsTwo = this.friendShipRepository.findFriendShip(request.getFriendId(),
                request.getUserId());

        if (fsOne != null) {

            this.friendShipRepository.deleteById(fsOne.getId());
        }

        if (fsTwo != null) {

            this.friendShipRepository.deleteById(fsTwo.getId());
        }

    }

    public void createFriend(Long userOneId, Long userTwoId) {
        User userOne = this.userService.getUserById(userOneId);
        User userTwo = this.userService.getUserById(userTwoId);

        if (this.friendRepository.checkIfExistsByFriend(userOne.getId(),
                userTwo.getId()) == null) {

            this.friendRepository.save(new Friend(userOne, userTwo));
            return;
        }

        if (this.friendRepository.checkIfExistsByFriend(userTwo.getId(),
                userOne.getId()) == null) {

            this.friendRepository.save(new Friend(userOne, userTwo));
        }

    }

    public FriendsPaginationDto getFriends(Long userId, int page, int pageSize, String direction) {
        int currentPage = MyUtils.paginate(page, direction);
        Pageable paging = PageRequest.of(currentPage, pageSize, Sort.by("id"));
        Page<Long> friendsIds = this.friendRepository.getFriendsIds(userId, paging);

        return new FriendsPaginationDto(
                this.userService.getFriends(friendsIds.getContent()),
                friendsIds.getTotalPages(),
                currentPage,
                direction);
    }
}
