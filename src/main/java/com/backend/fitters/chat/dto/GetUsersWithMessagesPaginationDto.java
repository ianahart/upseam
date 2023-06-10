package com.backend.fitters.chat.dto;

import java.util.List;

public class GetUsersWithMessagesPaginationDto {
    private List<GetUserWithMessageDto> friends;
    private int totalPages;
    private int page;

    public GetUsersWithMessagesPaginationDto() {

    }

    public GetUsersWithMessagesPaginationDto(
            List<GetUserWithMessageDto> friends,
            int totalPages,
            int page) {
        this.friends = friends;
        this.totalPages = totalPages;
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public List<GetUserWithMessageDto> getFriends() {
        return friends;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setFriends(List<GetUserWithMessageDto> friends) {
        this.friends = friends;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
