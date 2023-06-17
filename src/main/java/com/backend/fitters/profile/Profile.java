package com.backend.fitters.profile;

import com.backend.fitters.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
    @Column(name = "specialities", columnDefinition = "TEXT")
    private String specialities;
    @Column(name = "pricing")
    private String pricing;
    @Column(name = "site")
    private String site;
    @Column(name = "bio")
    private String bio;
    @Column(name = "state", length = 2)
    private String state;

    @JsonIgnore
    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
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
            String pricing,
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

    public String getPricing() {
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

    public void setPricing(String pricing) {
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((avatarUrl == null) ? 0 : avatarUrl.hashCode());
        result = prime * result + ((avatarFileName == null) ? 0 : avatarFileName.hashCode());
        result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((specialities == null) ? 0 : specialities.hashCode());
        result = prime * result + ((pricing == null) ? 0 : pricing.hashCode());
        result = prime * result + ((site == null) ? 0 : site.hashCode());
        result = prime * result + ((bio == null) ? 0 : bio.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Profile other = (Profile) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (avatarUrl == null) {
            if (other.avatarUrl != null)
                return false;
        } else if (!avatarUrl.equals(other.avatarUrl))
            return false;
        if (avatarFileName == null) {
            if (other.avatarFileName != null)
                return false;
        } else if (!avatarFileName.equals(other.avatarFileName))
            return false;
        if (zipCode == null) {
            if (other.zipCode != null)
                return false;
        } else if (!zipCode.equals(other.zipCode))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (specialities == null) {
            if (other.specialities != null)
                return false;
        } else if (!specialities.equals(other.specialities))
            return false;
        if (pricing == null) {
            if (other.pricing != null)
                return false;
        } else if (!pricing.equals(other.pricing))
            return false;
        if (site == null) {
            if (other.site != null)
                return false;
        } else if (!site.equals(other.site))
            return false;
        if (bio == null) {
            if (other.bio != null)
                return false;
        } else if (!bio.equals(other.bio))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

}
