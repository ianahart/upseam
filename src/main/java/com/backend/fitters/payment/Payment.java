package com.backend.fitters.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
@Table(name = "payment")

public class Payment {

    @Id
    @SequenceGenerator(name = "payment_sequence", sequenceName = "payment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "email")
    private String email;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "stripe_id")
    private String stripeId;

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne()
    private User customer;

    @JoinColumn(name = "biller_id", referencedColumnName = "id")
    @ManyToOne
    private User biller;

    public Payment() {

    }

    public Payment(
            Long id,
            BigDecimal amount,
            String email,
            String stripeId,
            User customer,
            User biller,
            Timestamp createdAt) {

        this.id = id;
        this.amount = amount;
        this.email = email;
        this.stripeId = stripeId;
        this.customer = customer;
        this.biller = biller;
        this.createdAt = createdAt;
    }

    public Payment(
            BigDecimal amount,
            String email,
            String stripeId,
            User customer,
            User biller) {

        this.amount = amount;
        this.email = email;
        this.stripeId = stripeId;
        this.customer = customer;
        this.biller = biller;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public User getBiller() {
        return biller;
    }

    public User getCustomer() {
        return customer;
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setBiller(User biller) {
        this.biller = biller;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

}
