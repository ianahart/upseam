package com.backend.fitters.review.dto;

public class CheckExistsDto {
    private Long reviewId;

    public CheckExistsDto() {

    }

    public CheckExistsDto(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }
}
