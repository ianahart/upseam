package com.backend.fitters.bid;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.backend.fitters.cloth.Cloth;
import com.backend.fitters.user.User;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "bid")
public class Bid {

    @Id
    @SequenceGenerator(name = "bid_sequence", sequenceName = "bid_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bid_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
    @Column(name = "bid")
    private BigDecimal bid;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne()
    private User user;
    @JoinColumn(name = "cloth_id", referencedColumnName = "id")
    @ManyToOne
    private Cloth cloth;

    public Bid() {

    }

    public Bid(Long id, BigDecimal bid, User user, Cloth cloth) {
        this.id = id;
        this.bid = bid;
        this.user = user;
        this.cloth = cloth;
    }

    public Bid(BigDecimal bid, User user, Cloth cloth) {
        this.bid = bid;
        this.user = user;
        this.cloth = cloth;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public User getUser() {
        return user;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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
        result = prime * result + ((bid == null) ? 0 : bid.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((cloth == null) ? 0 : cloth.hashCode());
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
        Bid other = (Bid) obj;
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
        if (bid == null) {
            if (other.bid != null)
                return false;
        } else if (!bid.equals(other.bid))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (cloth == null) {
            if (other.cloth != null)
                return false;
        } else if (!cloth.equals(other.cloth))
            return false;
        return true;
    }

}
