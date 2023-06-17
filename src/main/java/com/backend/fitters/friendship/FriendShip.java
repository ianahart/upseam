package com.backend.fitters.friendship;

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
@Table(name = "friendship")
public class FriendShip {
    @Id
    @SequenceGenerator(name = "friendship_sequence", sequenceName = "friendship_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friendship_sequence")
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
    @ManyToOne()
    private User requester;
    @ManyToOne()
    @JoinColumn(name = "requestee_id", referencedColumnName = "id")
    private User requestee;

    public FriendShip() {

    }

    public FriendShip(
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

    public FriendShip(
            Boolean pending,
            Boolean accepted,
            Boolean declined,
            User requester,
            User requestee

    ) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
        result = prime * result + ((pending == null) ? 0 : pending.hashCode());
        result = prime * result + ((accepted == null) ? 0 : accepted.hashCode());
        result = prime * result + ((declined == null) ? 0 : declined.hashCode());
        result = prime * result + ((requester == null) ? 0 : requester.hashCode());
        result = prime * result + ((requestee == null) ? 0 : requestee.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FriendShip other = (FriendShip) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        } else if (!updatedAt.equals(other.updatedAt))
            return false;
        if (pending == null) {
            if (other.pending != null)
                return false;
        } else if (!pending.equals(other.pending))
            return false;
        if (accepted == null) {
            if (other.accepted != null)
                return false;
        } else if (!accepted.equals(other.accepted))
            return false;
        if (declined == null) {
            if (other.declined != null)
                return false;
        } else if (!declined.equals(other.declined))
            return false;
        if (requester == null) {
            if (other.requester != null)
                return false;
        } else if (!requester.equals(other.requester))
            return false;
        if (requestee == null) {
            if (other.requestee != null)
                return false;
        } else if (!requestee.equals(other.requestee))
            return false;
        return true;
    }

}
