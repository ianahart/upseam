package com.backend.fitters.chat;

import java.sql.Timestamp;

import com.backend.fitters.user.User;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @SequenceGenerator(name = "chat_message_sequence", sequenceName = "chat_message_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_message_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "content")
    private String content;

    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    @ManyToOne()
    private User sender;

    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    @ManyToOne
    private User receiver;

    public ChatMessage() {

    }

    public ChatMessage(
            Long id,
            String content,
            User sender,
            User receiver) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    public ChatMessage(
            String content,
            User sender,
            User receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Long getId() {

        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public User getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
