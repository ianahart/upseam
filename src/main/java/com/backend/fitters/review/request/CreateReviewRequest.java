package com.backend.fitters.review.request;

public class CreateReviewRequest {

    private Long clothId;
    private Long revieweeUserId;
    private Long reviewerUserId;
    private Integer rating;
    private String text;

    public CreateReviewRequest() {

    }

    public CreateReviewRequest(
            Long clothId,
            Long revieweeUserId,
            Long reviewerUserId,
            Integer rating,
            String text) {
        this.clothId = clothId;
        this.revieweeUserId = revieweeUserId;
        this.reviewerUserId = reviewerUserId;
        this.rating = rating;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getRating() {
        return rating;
    }

    public Long getClothId() {
        return clothId;
    }

    public Long getRevieweeUserId() {
        return revieweeUserId;
    }

    public Long getReviewerUserId() {
        return reviewerUserId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setClothId(Long clothId) {
        this.clothId = clothId;
    }

    public void setRevieweeUserId(Long revieweeUserId) {
        this.revieweeUserId = revieweeUserId;
    }

    public void setReviewerUserId(Long reviewerUserId) {
        this.reviewerUserId = reviewerUserId;
    }

}
