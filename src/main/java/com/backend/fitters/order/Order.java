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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((complete == null) ? 0 : complete.hashCode());
        result = prime * result + ((bid == null) ? 0 : bid.hashCode());
        result = prime * result + ((receiverUser == null) ? 0 : receiverUser.hashCode());
        result = prime * result + ((bidUser == null) ? 0 : bidUser.hashCode());
        result = prime * result + ((cloth == null) ? 0 : cloth.hashCode());
        result = prime * result + ((invoices == null) ? 0 : invoices.hashCode());
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
        Order other = (Order) obj;
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
        if (complete == null) {
            if (other.complete != null)
                return false;
        } else if (!complete.equals(other.complete))
            return false;
        if (bid == null) {
            if (other.bid != null)
                return false;
        } else if (!bid.equals(other.bid))
            return false;
        if (receiverUser == null) {
            if (other.receiverUser != null)
                return false;
        } else if (!receiverUser.equals(other.receiverUser))
            return false;
        if (bidUser == null) {
            if (other.bidUser != null)
                return false;
        } else if (!bidUser.equals(other.bidUser))
            return false;
        if (cloth == null) {
            if (other.cloth != null)
                return false;
        } else if (!cloth.equals(other.cloth))
            return false;
        if (invoices == null) {
            if (other.invoices != null)
                return false;
        } else if (!invoices.equals(other.invoices))
            return false;
        return true;
    }

}
