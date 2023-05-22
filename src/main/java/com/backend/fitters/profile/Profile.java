package com.backend.fitters.profile;

import com.backend.fitters.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile() {

    }

    public Profile(Long id, String avatarUrl, String avatarFileName) {
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.avatarFileName = avatarFileName;
    }

    public Long getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getAvatarFileName() {
        return avatarFileName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setAvatarFileName(String avatarFileName) {
        this.avatarFileName = avatarFileName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
