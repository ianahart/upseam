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
            BigDecimal bid) {
        this.id = id;
        this.createdAt = createdAt;
        this.user = user;
        this.order = order;
        this.owner = owner;
        this.bid = bid;
    }

    public Invoice(
            User user,
            Order order,
            User owner,
            BigDecimal bid) {
        this.user = user;
        this.order = order;
        this.owner = owner;
        this.bid = bid;
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

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
