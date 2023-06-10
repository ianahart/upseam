package com.backend.fitters.chat.dto;

public interface GetUsersWithMessagesDto {
    Long getReceiverUserId();

    Long getId();

    String getContent();

    String getFirstName();

    String getLastName();

}
