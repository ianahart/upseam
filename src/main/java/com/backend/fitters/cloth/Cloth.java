package com.backend.fitters.cloth;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.backend.fitters.bid.Bid;
import com.backend.fitters.order.Order;
import com.backend.fitters.user.User;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "cloth")
public class Cloth {
    @Id
    @SequenceGenerator(name = "cloth_sequence", sequenceName = "cloth_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cloth_sequence")
    @Column(name = "id")
    private Long id;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "cloth_url")
    private String clothUrl;
    @Column(name = "cloth_filename")
    private String clothFilename;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "size")
    private String size;
    @Column(name = "description")
    private String description;
    @Column(name = "closed")
    private Boolean closed;
    @Column(name = "closedId")
    private Long closedId;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "cloth")
    private List<Bid> bids;

    @OneToMany(mappedBy = "cloth")
    private List<Order> orders;

    public Cloth() {

    }

    public Cloth(Long id,
            Timestamp createdAt,
            Timestamp updatedAt,
            String clothUrl,
            String clothFilename,
            LocalDate dueDate,
            String size,
            String description, User user,
            Boolean closed,
            Long closedId) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.clothUrl = clothUrl;
        this.clothFilename = clothFilename;
        this.dueDate = dueDate;
        this.size = size;
        this.description = description;
        this.user = user;
        this.closed = closed;
        this.closedId = closedId;
    }

    public Cloth(
            String clothUrl,
            String clothFilename,
            LocalDate dueDate,
            String size,
            String description,
            User user,
            Boolean closed) {
        this.clothUrl = clothUrl;
        this.clothFilename = clothFilename;
        this.dueDate = dueDate;
        this.size = size;
        this.description = description;
        this.user = user;
        this.closed = closed;
    }

    public Long getId() {
        return id;
    }

    public Long getClosedId() {
        return closedId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public User getUser() {
        return user;
    }

    public Boolean getClosed() {
        return closed;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public String getSize() {
        return size;
    }

    public String getClothUrl() {
        return clothUrl;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public String getClothFilename() {
        return clothFilename;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setClothUrl(String clothUrl) {
        this.clothUrl = clothUrl;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public void setClothFilename(String clothFilename) {
        this.clothFilename = clothFilename;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClosedId(Long closedId) {
        this.closedId = closedId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
        result = prime * result + ((clothUrl == null) ? 0 : clothUrl.hashCode());
        result = prime * result + ((clothFilename == null) ? 0 : clothFilename.hashCode());
        result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((closed == null) ? 0 : closed.hashCode());
        result = prime * result + ((closedId == null) ? 0 : closedId.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((bids == null) ? 0 : bids.hashCode());
        result = prime * result + ((orders == null) ? 0 : orders.hashCode());
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
        Cloth other = (Cloth) obj;
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
        if (clothUrl == null) {
            if (other.clothUrl != null)
                return false;
        } else if (!clothUrl.equals(other.clothUrl))
            return false;
        if (clothFilename == null) {
            if (other.clothFilename != null)
                return false;
        } else if (!clothFilename.equals(other.clothFilename))
            return false;
        if (dueDate == null) {
            if (other.dueDate != null)
                return false;
        } else if (!dueDate.equals(other.dueDate))
            return false;
        if (size == null) {
            if (other.size != null)
                return false;
        } else if (!size.equals(other.size))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (closed == null) {
            if (other.closed != null)
                return false;
        } else if (!closed.equals(other.closed))
            return false;
        if (closedId == null) {
            if (other.closedId != null)
                return false;
        } else if (!closedId.equals(other.closedId))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (bids == null) {
            if (other.bids != null)
                return false;
        } else if (!bids.equals(other.bids))
            return false;
        if (orders == null) {
            if (other.orders != null)
                return false;
        } else if (!orders.equals(other.orders))
            return false;
        return true;
    }

}
