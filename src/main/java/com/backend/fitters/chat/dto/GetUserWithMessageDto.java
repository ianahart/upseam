package com.backend.fitters.chat.dto;

public class GetUserWithMessageDto {
    private Long receiverUserId;
    private Long id;
    private String content;
    private String firstName;
    private String lastName;
    private String avatarUrl;

    public GetUserWithMessageDto() {

    }

    public GetUserWithMessageDto(
            Long receiverUserId,
            Long id,
            String content,
            String firstName,
            String lastName) {
        this.receiverUserId = receiverUserId;
        this.id = id;
        this.content = content;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Long getReceiverUserId() {
        return receiverUserId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setReceiverUserId(Long receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
