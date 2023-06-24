package com.backend.fitters.comment.dto;

import java.util.List;

public class PaginationCommentDto {
    private List<CommentDto> comments;
    private int page;
    private int pageSize;
    private int totalPages;
    private String direction;

    public PaginationCommentDto() {

    }

    public PaginationCommentDto(
            List<CommentDto> comments,
            int page,
            int pageSize,
            int totalPages,
            String direction) {
        this.comments = comments;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.direction = direction;
    }

    public List<CommentDto> getComments() {

        return comments;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getDirection() {
        return direction;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
