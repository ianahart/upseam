package com.backend.fitters.review.response;

import com.backend.fitters.review.dto.PaginationReviewsDto;

public class GetReviewsResponse {

    private String message;
    private PaginationReviewsDto data;

    public GetReviewsResponse() {

    }

    public GetReviewsResponse(String message, PaginationReviewsDto data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public PaginationReviewsDto getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(PaginationReviewsDto data) {
        this.data = data;
    }
}
