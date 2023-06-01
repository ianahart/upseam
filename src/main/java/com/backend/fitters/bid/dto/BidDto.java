package com.backend.fitters.bid.dto;

import java.sql.Timestamp;
import java.math.BigDecimal;

public class BidDto {
    private Long id;
    private Timestamp createdAt;
    private BigDecimal bid;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private Long profileId;

    public BidDto() {

    }

    public BidDto(
            Long id,
            Timestamp createdAt,
            BigDecimal bid,
            String firstName,
            String lastName,
            String avatarUrl,
            Long profileId) {

        this.id = id;
        this.createdAt = createdAt;
        this.bid = bid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
        this.profileId = profileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Long getProfileId() {
        return profileId;
    }

    public String getLastName() {
        return lastName;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
