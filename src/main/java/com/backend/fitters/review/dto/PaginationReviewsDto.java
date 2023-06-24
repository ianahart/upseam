package com.backend.fitters.review.dto;

import java.util.List;

public class PaginationReviewsDto {
    private List<ReviewsDto> reviews;
    private int totalPages;
    private int page;
    private String direction;
    private int pageSize;

    public PaginationReviewsDto() {

    }

    public PaginationReviewsDto(
            List<ReviewsDto> reviews,
            int totalPages,
            int page,
            String direction,
            int pageSize) {

        this.reviews = reviews;
        this.totalPages = totalPages;
        this.page = page;
        this.direction = direction;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public List<ReviewsDto> getReviews() {
        return reviews;
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

    public void setReviews(List<ReviewsDto> reviews) {
        this.reviews = reviews;
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
