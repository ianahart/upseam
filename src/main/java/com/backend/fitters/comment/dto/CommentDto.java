package com.backend.fitters.comment.dto;

import java.sql.Timestamp;

public class CommentDto {
    private Long commentId;
    private String text;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private Timestamp createdAt;
    private Long userId;

    public CommentDto() {

    }

    public CommentDto(
            Long commentId,
            String text,
            String firstName,
            String lastName,
            String avatarUrl,
            Timestamp createdAt,
            Long userId) {
        this.commentId = commentId;
        this.text = text;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
