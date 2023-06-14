package com.backend.fitters.user;

import java.util.Objects;

import com.backend.fitters.token.Token;
import com.backend.fitters.profile.Profile;
import com.backend.fitters.refreshtoken.RefreshToken;
import com.backend.fitters.shipping.Shipping;
import com.backend.fitters.passwordreset.PasswordReset;
import com.backend.fitters.bid.Bid;
import com.backend.fitters.chat.ChatMessage;
import com.backend.fitters.cloth.Cloth;
import com.backend.fitters.friend.Friend;
import com.backend.fitters.friendship.FriendShip;
import com.backend.fitters.order.Order;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity()
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "_user_sequence", sequenceName = "_user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_user_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;
    @Column(name = "email", length = 150, nullable = false)
    private String email;
    @Column(name = "password", length = 150, nullable = false)
    private String password;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Transient
    private String abbreviation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @OneToMany(mappedBy = "user")
    private List<RefreshToken> refreshTokens;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "user")
    private List<PasswordReset> passwordResets;

    @OneToMany(mappedBy = "user")
    private List<Cloth> clothes;

    @OneToMany(mappedBy = "user")
    private List<Bid> bids;

    @OneToMany(mappedBy = "user")
    private List<Shipping> shippings;

    @OneToMany(mappedBy = "bidUser")
    private List<Order> orders;

    @OneToMany(mappedBy = "requestee")
    private List<FriendShip> friendRequestees;

    @OneToMany(mappedBy = "requester")
    private List<FriendShip> friendRequesters;

    @OneToMany(mappedBy = "sender")
    private List<ChatMessage> senders;

    @OneToMany(mappedBy = "receiver")
    private List<ChatMessage> receivers;

    @OneToMany(mappedBy = "friend")
    private List<Friend> friends;

    @OneToMany(mappedBy = "user")
    private List<Friend> users;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {

    }

    public User(Long id, String firstName, String lastName, String email, String password, Role role, Profile profile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profile = profile;
    }

    public User(String firstName, String lastName, String email, String password, Role role, Profile profile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profile = profile;
    }

    public String getAbbreviation() {
        return firstName.substring(0, 1).toUpperCase() + lastName.substring(0, 1).toUpperCase();
    }

    public Long getId() {
        return id;
    }

    public List<Shipping> getShippings() {
        return shippings;
    }

    public Profile getProfile() {
        return profile;
    }

    public Role getRole() {
        return role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<ChatMessage> getSenders() {
        return senders;
    }

    public List<ChatMessage> getReceivers() {
        return receivers;
    }

    public List<FriendShip> getFriendRequestees() {
        return friendRequestees;
    }

    public List<FriendShip> getFriendRequesters() {
        return friendRequesters;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public List<Friend> getUsers() {
        return users;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public List<Cloth> getClothes() {
        return clothes;
    }

    public List<Bid> getBids() {
        return bids;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClothes(List<Cloth> clothes) {

    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setSenders(List<ChatMessage> senders) {
        this.senders = senders;
    }

    public void setReceivers(List<ChatMessage> receivers) {
        this.receivers = receivers;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;

    }

    public void setUsers(List<Friend> users) {
        this.users = users;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFriendRequestees(List<FriendShip> friendRequestees) {
        this.friendRequestees = friendRequestees;
    }

    public void setFriendRequesters(List<FriendShip> friendRequesters) {
        this.friendRequesters = friendRequesters;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setShippings(List<Shipping> shippings) {
        this.shippings = shippings;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName=" + lastName + '\'' +
                ", email=" + email +
                ", role=" + role +
                ", password=" + password +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User user))
            return false;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password) && Objects.equals(role, user.role) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, role, password);
    }
}
