package com.backend.fitters.order;

import java.sql.Timestamp;
import java.util.List;
import java.math.BigDecimal;

import com.backend.fitters.cloth.Cloth;
import com.backend.fitters.invoice.Invoice;
import com.backend.fitters.user.User;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity()
@Table(name = "_order")
public class Order {

    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @Column(name = "id")
    private Long id;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "complete")
    private Boolean complete;
    @Column(name = "bid")
    private BigDecimal bid;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne()
    private User receiverUser;

    @JoinColumn(name = "biduser_id", referencedColumnName = "id")
    @ManyToOne()
    private User bidUser;

    @JoinColumn(name = "cloth_id", referencedColumnName = "id")
    @ManyToOne()
    private Cloth cloth;

    @OneToMany(mappedBy = "order")
    private List<Invoice> invoices;

    public Order() {

    }

    public Order(User receiverUser, Cloth cloth, User bidUser, Boolean complete, BigDecimal bid) {
        this.receiverUser = receiverUser;
        this.cloth = cloth;
        this.bidUser = bidUser;
        this.complete = complete;
        this.bid = bid;
    }

    public Order(
            Long id,
            Timestamp createdAt,
            User receiverUser,
            Cloth cloth,
            User bidUser,
            Boolean complete,
            BigDecimal bid) {

        this.id = id;
        this.createdAt = createdAt;
        this.receiverUser = receiverUser;
        this.cloth = cloth;
        this.bidUser = bidUser;
        this.complete = complete;
        this.bid = bid;
    }

    public Long getId() {
        return id;
    }

    public Boolean getComplete() {
        return complete;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public BigDecimal getBid() {

        return bid;
    }

    public User getBidUser() {
        return bidUser;
    }

    public User getReceiverUser() {
        return receiverUser;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public void setReceiverUser(User receiverUser) {
        this.receiverUser = receiverUser;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setBidUser(User bidUser) {
        this.bidUser = bidUser;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
