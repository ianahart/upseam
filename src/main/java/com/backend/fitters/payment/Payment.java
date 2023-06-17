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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((stripeId == null) ? 0 : stripeId.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((biller == null) ? 0 : biller.hashCode());
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
        Payment other = (Payment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (stripeId == null) {
            if (other.stripeId != null)
                return false;
        } else if (!stripeId.equals(other.stripeId))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (biller == null) {
            if (other.biller != null)
                return false;
        } else if (!biller.equals(other.biller))
            return false;
        return true;
    }

}
