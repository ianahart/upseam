package com.backend.fitters.chat.response;

import java.util.List;

import com.backend.fitters.chat.dto.ChatMessageDto;

public class GetChatMessagesResponse {

    private String message;
    private List<ChatMessageDto> chatMessages;

    public GetChatMessagesResponse() {

    }

    public GetChatMessagesResponse(String message, List<ChatMessageDto> chatMessages) {
        this.message = message;
        this.chatMessages = chatMessages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ChatMessageDto> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessageDto> chatMessages) {
        this.chatMessages = chatMessages;
    }
}
