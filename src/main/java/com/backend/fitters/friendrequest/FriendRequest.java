package com.backend.fitters.friendrequest;

import java.sql.Timestamp;

import com.backend.fitters.user.User;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "friend_request")
public class FriendRequest {
    @Id
    @SequenceGenerator(name = "friend_request", sequenceName = "friendrequest", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_request")
    @Column(name = "id")
    private Long id;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "pending", nullable = false)
    private Boolean pending;
    @Column(name = "accepted", nullable = false)
    private Boolean accepted;
    @Column(name = "declined", nullable = false)
    private Boolean declined;
    @JoinColumn(name = "requester_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User requester;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requestee_id", referencedColumnName = "id")
    private User requestee;

    public FriendRequest() {

    }

    public FriendRequest(
            Long id,
            Timestamp createdAt,
            Timestamp updatedAt,
            Boolean pending,
            Boolean accepted,
            Boolean declined,
            User requester,
            User requestee

    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pending = pending;
        this.accepted = accepted;
        this.declined = declined;
        this.requester = requester;
        this.requestee = requestee;

    }

    public Long getId() {
        return id;
    }

    public Boolean getPending() {
        return pending;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public Boolean getDeclined() {
        return declined;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public User getRequestee() {
        return requestee;
    }

    public User getRequester() {
        return requester;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public void setDeclined(Boolean declined) {
        this.declined = declined;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setRequestee(User requestee) {
        this.requestee = requestee;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
