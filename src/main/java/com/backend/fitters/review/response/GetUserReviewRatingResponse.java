package com.backend.fitters.review.response;

public class GetUserReviewRatingResponse {
    private String message;
    private Integer rating;

    public GetUserReviewRatingResponse() {

    }

    public GetUserReviewRatingResponse(String message, Integer rating) {
        this.message = message;
        this.rating = rating;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}
