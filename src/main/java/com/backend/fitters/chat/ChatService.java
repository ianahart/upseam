package com.backend.fitters.chat;

import java.util.List;
import com.backend.fitters.chat.dto.ChatMessageDto;
import com.backend.fitters.chat.request.SendChatMessageRequest;
import com.backend.fitters.advice.BadRequestException;
import com.backend.fitters.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
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
