package com.backend.fitters.chat.dto;

public interface DeleteMessageDto {
    Long getReceiverUserId();

    Long getSenderUserId();

    Long getChatId();
}
