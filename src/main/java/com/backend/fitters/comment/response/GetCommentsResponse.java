package com.backend.fitters.comment.response;

import com.backend.fitters.comment.dto.PaginationCommentDto;

public class GetCommentsResponse {
    private String message;
    private PaginationCommentDto data;

    public GetCommentsResponse() {

    }

    public GetCommentsResponse(String message, PaginationCommentDto data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public PaginationCommentDto getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(PaginationCommentDto data) {
        this.data = data;
    }
}
