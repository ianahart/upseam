package com.backend.fitters.invoice;

import java.sql.Timestamp;
import java.math.BigDecimal;

import com.backend.fitters.order.Order;
import com.backend.fitters.user.User;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity()
@Table(name = "invoice")
public class Invoice {
    @Id
    @SequenceGenerator(name = "invoice_sequence", sequenceName = "invoice_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_sequence")
    @Column(name = "id")
    private Long id;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "bid")
    private BigDecimal bid;
    @Column(name = "paid")
    private Boolean paid;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne()
    private User user;
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne()
    private Order order;
    @ManyToOne()
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    public Invoice() {

    }

    public Invoice(
            Long id,
            Timestamp createdAt,
            User user,
            Order order,
            User owner,
            BigDecimal bid,
            Boolean paid) {
        this.id = id;
        this.createdAt = createdAt;
        this.user = user;
        this.order = order;
        this.owner = owner;
        this.bid = bid;
        this.paid = paid;
    }

    public Invoice(
            User user,
            Order order,
            User owner,
            BigDecimal bid,
            Boolean paid) {
        this.user = user;
        this.order = order;
        this.owner = owner;
        this.bid = bid;
        this.paid = paid;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Boolean getPaid() {
        return paid;
    }

    public User getUser() {
        return user;
    }

    public Order getOrder() {
        return order;
    }

    public User getOwner() {
        return owner;
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

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((bid == null) ? 0 : bid.hashCode());
        result = prime * result + ((paid == null) ? 0 : paid.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
        Invoice other = (Invoice) obj;
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
        if (bid == null) {
            if (other.bid != null)
                return false;
        } else if (!bid.equals(other.bid))
            return false;
        if (paid == null) {
            if (other.paid != null)
                return false;
        } else if (!paid.equals(other.paid))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        return true;
    }

}
