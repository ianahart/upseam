package com.backend.fitters.chat.request;

public class SendChatMessageRequest {
    private String message;
    private Long receiverUserId;
    private Long senderUserId;

    public SendChatMessageRequest() {

    }

    public SendChatMessageRequest(
            String message,
            Long receiverUserId,
            Long senderUserId) {
        this.message = message;
        this.receiverUserId = receiverUserId;
        this.senderUserId = senderUserId;
    }

    public String getMessage() {
        return message;
    }

    public Long getSenderUserId() {
        return senderUserId;
    }

    public Long getReceiverUserId() {
        return receiverUserId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public void setReceiverUserId(Long receiverUserId) {
        this.receiverUserId = receiverUserId;
    }
}
