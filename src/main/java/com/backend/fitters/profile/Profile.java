package com.backend.fitters.profile;

import java.util.List;
import java.util.ArrayList;

import com.backend.fitters.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @SequenceGenerator(name = "profile_sequence", sequenceName = "profile_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column(name = "avatar_file_name")
    private String avatarFileName;
    @Column(name = "zip_code", length = 5)
    private String zipCode;
    @Column(name = "country", length = 255)
    private String country;
    @Column(name = "address", length = 255)
    private String address;
    @Column(name = "specialities")
    private String specialities;
    @Column(name = "pricing")
    private Integer pricing;
    @Column(name = "site")
    private String site;
    @Column(name = "bio")
    private String bio;
    @Column(name = "state", length = 2)
    private String state;

    @JsonIgnore
    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile() {

    }

    public Profile(Long id,
            String avatarUrl,
            String avatarFileName,
            String zipCode,
            String country,
            String address,
            String specialities,
            Integer pricing,
            String site,
            String bio,
            String state) {
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.avatarFileName = avatarFileName;
        this.zipCode = zipCode;
        this.country = country;
        this.address = address;
        this.specialities = specialities;
        this.pricing = pricing;
        this.site = site;
        this.bio = bio;
        this.state = state;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public String getSite() {
        return site;
    }

    public Integer getPricing() {
        return pricing;
    }

    public String getAvatarFileName() {
        return avatarFileName;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getSpecialities() {
        return specialities;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setAvatarUrl(String avatarUrl) {

        this.avatarUrl = avatarUrl;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setPricing(Integer pricing) {
        this.pricing = pricing;
    }

    public void setAvatarFileName(String avatarFileName) {
        this.avatarFileName = avatarFileName;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
