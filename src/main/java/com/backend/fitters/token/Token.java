package com.backend.fitters.token;

import java.util.Objects;
import com.backend.fitters.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity()
@Table(name = "token")
public class Token {

    @Id
    @SequenceGenerator(name = "token_sequence", sequenceName = "token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    private Long id;
    @Column(name = "token")
    private String token;
    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;
    @Column(name = "expired")
    private Boolean expired;
    @Column(name = "revoked")
    private Boolean revoked;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Token() {
    }

    public Token(Long id, String token, TokenType tokenType, Boolean expired, Boolean revoked) {
        this.id = id;
        this.token = token;
        this.tokenType = tokenType;
        this.expired = expired;
        this.revoked = revoked;
    }

    public Token(Long id, String token, TokenType tokenType, Boolean expired, Boolean revoked, User user) {
        this.id = id;
        this.token = token;
        this.tokenType = tokenType;
        this.expired = expired;
        this.revoked = revoked;
        this.user = user;
    }

    public Token(String token, TokenType tokenType, Boolean expired, Boolean revoked, User user) {
        this.token = token;
        this.tokenType = tokenType;
        this.expired = expired;
        this.revoked = revoked;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public Boolean getExpired() {
        return expired;
    }

    public Boolean getRevoked() {
        return revoked;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public void setRevoked(Boolean revoked) {
        this.revoked = revoked;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Token token1))
            return false;
        return Objects.equals(id, token1.id) && Objects.equals(token, token1.token) && tokenType == token1.tokenType
                && Objects.equals(expired, token1.expired) && Objects.equals(revoked, token1.revoked)
                && Objects.equals(user, token1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, tokenType, expired, revoked, user);
    }
}
