package com.backend.fitters.chat.response;

import com.backend.fitters.chat.dto.ChatMessageDto;

public class SendChatMessageResponse {
    private String message;
    private ChatMessageDto chatMessage;

    public SendChatMessageResponse() {

    }

    public SendChatMessageResponse(String message, ChatMessageDto chatMessage) {
        this.message = message;
        this.chatMessage = chatMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatMessageDto getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessageDto chatMessage) {
        this.chatMessage = chatMessage;
    }

}
