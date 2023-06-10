package com.backend.fitters.chat;

import java.util.ArrayList;
import java.util.List;
import com.backend.fitters.chat.dto.ChatMessageDto;
import com.backend.fitters.chat.dto.GetUserWithMessageDto;
import com.backend.fitters.chat.dto.GetUsersWithMessagesDto;
import com.backend.fitters.chat.dto.GetUsersWithMessagesPaginationDto;
import com.backend.fitters.chat.request.SendChatMessageRequest;
import com.backend.fitters.advice.BadRequestException;
import com.backend.fitters.user.UserService;
import com.backend.fitters.util.MyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    private List<GetUserWithMessageDto> createUserWithMessage(Page<GetUsersWithMessagesDto> result) {
        List<GetUserWithMessageDto> friends = new ArrayList<>();

        for (GetUsersWithMessagesDto friend : result.getContent()) {
            GetUserWithMessageDto newFriend = new GetUserWithMessageDto(
                    friend.getReceiverUserId(),
                    friend.getId(),
                    friend.getContent(),
                    friend.getFirstName(),
                    friend.getLastName());

            newFriend.setAvatarUrl(
                    this.userService.getUserById(newFriend.getReceiverUserId()).getProfile().getAvatarUrl());

            friends.add(newFriend);
        }
        return friends;
    }

    public GetUsersWithMessagesPaginationDto getUsersWithMessages(Long currentUserId, int page) {
        if (currentUserId == null || currentUserId == 0) {
            throw new BadRequestException("No current userid available");
        }
        int currentPage = MyUtils.paginate(page, "next");
        Pageable paging = PageRequest.of(currentPage, 5, Sort.by("id"));
        Page<GetUsersWithMessagesDto> result = this.chatRepository.getUsersWithMessages(currentUserId, paging);
        List<GetUserWithMessageDto> friends = createUserWithMessage(result);

        return new GetUsersWithMessagesPaginationDto(friends, result.getTotalPages(), currentPage);
    }

    public List<ChatMessageDto> getChatMessages(Long receiverUserId, Long senderUserId) {
        if (receiverUserId == null || senderUserId == null) {
            throw new BadRequestException("Chat messages are missing user ids.");
        }

        return this.chatRepository.getChatMessages(receiverUserId, senderUserId);
    }

    public ChatMessageDto sendChatMessage(SendChatMessageRequest request) {
        ChatMessage chatMessage = this.chatRepository.save(new ChatMessage(
                request.getMessage(),
                this.userService.getUserById(request.getSenderUserId()),
                this.userService.getUserById(request.getReceiverUserId())));

        return this.chatRepository.getChatMessage(chatMessage.getId(), request.getSenderUserId(),
                request.getReceiverUserId());
    }

}
