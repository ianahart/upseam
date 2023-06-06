package com.backend.fitters.friend.dto;

import java.util.List;

import com.backend.fitters.user.dto.GetFriendsDto;

public class FriendsPaginationDto {
    private List<GetFriendsDto> content;
    private Integer totalPages;
    private Integer page;
    private String direction;

    public FriendsPaginationDto() {

    }

    public FriendsPaginationDto(
            List<GetFriendsDto> content,
            Integer totalPages,
            Integer page,
            String direction) {
        this.content = content;
        this.totalPages = totalPages;
        this.page = page;
        this.direction = direction;
    }

    public Integer getPage() {
        return page;
    }

    public String getDirection() {
        return direction;
    }

    public List<GetFriendsDto> getContent() {
        return content;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setContent(List<GetFriendsDto> content) {
        this.content = content;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
