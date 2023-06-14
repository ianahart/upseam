package com.backend.fitters.shipping;

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
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity()
@Table(name = "shipping")
public class Shipping {

    @Id
    @SequenceGenerator(name = "shipping_sequence", sequenceName = "shipping_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipping_sequence")
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne()
    private User user;
    @Size(min = 5, max = 5)
    @Column(name = "zipCode", nullable = false)
    private String zipCode;
    @Column(name = "state")
    private String state;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "shipping_type", nullable = false)
    private String shippingType;
    @Column(name = "shipping_value", nullable = false)
    private String shippingValue;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    public Shipping() {

    }

    public Shipping(
            User user,
            String zipCode,
            String state,
            String country,
            String address,
            String firstName,
            String lastName,
            String shippingType,
            String shippingValue) {
        this.user = user;
        this.zipCode = zipCode;
        this.state = state;
        this.country = country;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shippingType = shippingType;
        this.shippingValue = shippingValue;
    }

    public Shipping(
            Long id,
            User user,
            String zipCode,
            String state,
            String country,
            String address,
            String firstName,
            String lastName,
            String shippingType,
            String shippingValue,
            Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.zipCode = zipCode;
        this.state = state;
        this.country = country;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shippingType = shippingType;
        this.shippingValue = shippingValue;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getLastName() {
        return lastName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getShippingType() {
        return shippingType;
    }

    public String getShippingValue() {
        return shippingValue;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public void setShippingValue(String shippingValue) {
        this.shippingValue = shippingValue;
    }
}
