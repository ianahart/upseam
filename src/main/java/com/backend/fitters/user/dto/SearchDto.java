package com.backend.fitters.user.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public class SearchDto {

    private List<GetUsersDto> users;
    private int totalPages;
    private int page;

    public SearchDto() {
    }

    public SearchDto(List<GetUsersDto> users, int totalPages, int page) {
        this.users = users;
        this.totalPages = totalPages;
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public List<GetUsersDto> getUsers() {
        return users;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setUsers(List<GetUsersDto> users) {
        this.users = users;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
