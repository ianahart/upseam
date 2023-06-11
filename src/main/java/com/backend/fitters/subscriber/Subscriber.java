package com.backend.fitters.subscriber;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity()
@Table(name = "subscriber")
public class Subscriber {

    @Id
    @SequenceGenerator(name = "subscriber_sequence", sequenceName = "subscriber_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "email", length = 200)
    private String email;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    public Subscriber() {

    }

    public Subscriber(
            Long id,
            String email,
            Timestamp createdAt) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Subscriber(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
