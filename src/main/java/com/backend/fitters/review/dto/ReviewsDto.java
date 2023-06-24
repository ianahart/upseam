package com.backend.fitters.review.dto;

import java.sql.Timestamp;

public class ReviewsDto {

    private Long reviewId;
    private String text;
    private Integer rating;
    private String firstName;
    private String lastName;
    private Timestamp createdAt;
    private String avatarUrl;

    public ReviewsDto() {
    }

    public ReviewsDto(
            Long reviewId,
            String text,
            Integer rating,
            String firstName,
            String lastName,
            Timestamp createdAt,
            String avatarUrl) {
        this.reviewId = reviewId;
        this.text = text;
        this.rating = rating;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.avatarUrl = avatarUrl;
    }

    public String getText() {
        return text;
    }

    public Integer getRating() {
        return rating;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
