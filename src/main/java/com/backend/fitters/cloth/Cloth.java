package com.backend.fitters.cloth;

import java.sql.Timestamp;
import java.time.LocalDate;
import com.backend.fitters.user.User;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne()
    private User user;

    public Cloth() {

    }

    public Cloth(Long id,
            Timestamp createdAt,
            Timestamp updatedAt,
            String clothUrl,
            String clothFilename,
            LocalDate dueDate,
            String size,
            String description, User user) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.clothUrl = clothUrl;
        this.clothFilename = clothFilename;
        this.dueDate = dueDate;
        this.size = size;
        this.description = description;
        this.user = user;
    }

    public Cloth(
            String clothUrl,
            String clothFilename,
            LocalDate dueDate,
            String size,
            String description,
            User user) {
        this.clothUrl = clothUrl;
        this.clothFilename = clothFilename;
        this.dueDate = dueDate;
        this.size = size;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
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

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setClothUrl(String clothUrl) {
        this.clothUrl = clothUrl;
    }

    public void setClothFilename(String clothFilename) {
        this.clothFilename = clothFilename;
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
}
