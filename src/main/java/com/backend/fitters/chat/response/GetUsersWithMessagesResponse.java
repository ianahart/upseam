package com.backend.fitters.chat.response;

import com.backend.fitters.chat.dto.GetUsersWithMessagesPaginationDto;

public class GetUsersWithMessagesResponse {
    private String message;
    private GetUsersWithMessagesPaginationDto pagination;

    public GetUsersWithMessagesResponse() {

    }

    public GetUsersWithMessagesResponse(String message, GetUsersWithMessagesPaginationDto pagination) {
        this.message = message;
        this.pagination = pagination;
    }

    public String getMessage() {
        return message;
    }

    public GetUsersWithMessagesPaginationDto getPagination() {
        return pagination;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPagination(GetUsersWithMessagesPaginationDto pagination) {
        this.pagination = pagination;
    }
}
