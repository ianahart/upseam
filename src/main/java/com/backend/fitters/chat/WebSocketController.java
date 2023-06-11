package com.backend.fitters.chat;

import com.backend.fitters.chat.dto.ChatMessageDto;
import com.backend.fitters.chat.request.SendChatMessageRequest;
import com.backend.fitters.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class WebSocketController {
    private SimpMessagingTemplate simpMessagingTemplate;
    private ChatService chatService;

    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate,
            ChatService chatService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatService = chatService;

    }

    @MessageMapping("private-message")
    public ChatMessageDto receiveMessage(@Payload SendChatMessageRequest message) {
        ChatMessageDto chatMessageDto = this.chatService.sendChatMessage(message);
        System.out.println(message);
        this.simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessageDto.getReceiverUserId()), "private",
                chatMessageDto);
        this.simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessageDto.getSenderUserId()), "private",
                chatMessageDto);

        return chatMessageDto;
    }
}
