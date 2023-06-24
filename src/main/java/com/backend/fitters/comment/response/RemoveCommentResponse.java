package com.backend.fitters.comment.response;

public class RemoveCommentResponse {
    private String message;

    public RemoveCommentResponse() {

    }

    public RemoveCommentResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
