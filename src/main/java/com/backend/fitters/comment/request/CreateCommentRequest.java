package com.backend.fitters.comment.request;

public class CreateCommentRequest {
    private Long userId;
    private Long clothId;
    private String text;

    public CreateCommentRequest() {

    }

    public CreateCommentRequest(
            Long userId,
            Long clothId,
            String text) {

        this.userId = userId;
        this.clothId = clothId;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getClothId() {
        return clothId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setClothId(Long clothId) {
        this.clothId = clothId;
    }
}
