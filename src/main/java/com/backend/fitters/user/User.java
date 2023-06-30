package com.backend.fitters.user;

import java.util.Objects;

import com.backend.fitters.token.Token;
import com.backend.fitters.profile.Profile;
import com.backend.fitters.refreshtoken.RefreshToken;
import com.backend.fitters.review.Review;
import com.backend.fitters.shipping.Shipping;
import com.backend.fitters.passwordreset.PasswordReset;
import com.backend.fitters.payment.Payment;
import com.backend.fitters.bid.Bid;
import com.backend.fitters.comment.Comment;
import com.backend.fitters.chat.ChatMessage;
import com.backend.fitters.cloth.Cloth;
import com.backend.fitters.friend.Friend;
import com.backend.fitters.friendship.FriendShip;
import com.backend.fitters.invoice.Invoice;
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

    @OneToMany(mappedBy = "customer")
    private List<Payment> customerPayments;

    @OneToMany(mappedBy = "biller")
    private List<Payment> billerPayments;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Cloth> clothes;

    @OneToMany(mappedBy = "user")
    private List<Bid> bids;

    @OneToMany(mappedBy = "user")
    private List<Shipping> shippings;

    @OneToMany(mappedBy = "bidUser")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "owner")
    private List<Invoice> ownerInvoices;

    @OneToMany(mappedBy = "requestee")
    private List<FriendShip> friendRequestees;

    @OneToMany(mappedBy = "requester")
    private List<FriendShip> friendRequesters;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<ChatMessage> senders;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<ChatMessage> receivers;

    @OneToMany(mappedBy = "reviewee")
    private List<Review> revieweeReviewers;

    @OneToMany(mappedBy = "reviewer")
    private List<Review> reviewerReviewers;

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

    public List<Comment> getComments() {
        return comments;
    }

    public List<Shipping> getShippings() {
        return shippings;
    }

    public List<Payment> getBillerPayments() {
        return billerPayments;
    }

    public List<Payment> getCustomerPayments() {
        return customerPayments;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public List<Review> getRevieweeReviewers() {
        return revieweeReviewers;
    }

    public List<Review> getReviewerReviewers() {
        return reviewerReviewers;
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
        this.clothes = clothes;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setRevieweeReviewers(List<Review> revieweeReviewers) {
        this.revieweeReviewers = revieweeReviewers;
    }

    public void setReviewerReviewers(List<Review> reviewerReviewers) {
        this.reviewerReviewers = reviewerReviewers;
    }

    public void setBillerPayments(List<Payment> billerPayments) {
        this.billerPayments = billerPayments;
    }

    public void setCustomerPayments(List<Payment> customerPayments) {
        this.customerPayments = customerPayments;
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

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((abbreviation == null) ? 0 : abbreviation.hashCode());
        result = prime * result + ((profile == null) ? 0 : profile.hashCode());
        result = prime * result + ((refreshTokens == null) ? 0 : refreshTokens.hashCode());
        result = prime * result + ((tokens == null) ? 0 : tokens.hashCode());
        result = prime * result + ((passwordResets == null) ? 0 : passwordResets.hashCode());
        result = prime * result + ((customerPayments == null) ? 0 : customerPayments.hashCode());
        result = prime * result + ((billerPayments == null) ? 0 : billerPayments.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((clothes == null) ? 0 : clothes.hashCode());
        result = prime * result + ((bids == null) ? 0 : bids.hashCode());
        result = prime * result + ((shippings == null) ? 0 : shippings.hashCode());
        result = prime * result + ((orders == null) ? 0 : orders.hashCode());
        result = prime * result + ((invoices == null) ? 0 : invoices.hashCode());
        result = prime * result + ((ownerInvoices == null) ? 0 : ownerInvoices.hashCode());
        result = prime * result + ((friendRequestees == null) ? 0 : friendRequestees.hashCode());
        result = prime * result + ((friendRequesters == null) ? 0 : friendRequesters.hashCode());
        result = prime * result + ((senders == null) ? 0 : senders.hashCode());
        result = prime * result + ((receivers == null) ? 0 : receivers.hashCode());
        result = prime * result + ((revieweeReviewers == null) ? 0 : revieweeReviewers.hashCode());
        result = prime * result + ((reviewerReviewers == null) ? 0 : reviewerReviewers.hashCode());
        result = prime * result + ((friends == null) ? 0 : friends.hashCode());
        result = prime * result + ((users == null) ? 0 : users.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (abbreviation == null) {
            if (other.abbreviation != null)
                return false;
        } else if (!abbreviation.equals(other.abbreviation))
            return false;
        if (profile == null) {
            if (other.profile != null)
                return false;
        } else if (!profile.equals(other.profile))
            return false;
        if (refreshTokens == null) {
            if (other.refreshTokens != null)
                return false;
        } else if (!refreshTokens.equals(other.refreshTokens))
            return false;
        if (tokens == null) {
            if (other.tokens != null)
                return false;
        } else if (!tokens.equals(other.tokens))
            return false;
        if (passwordResets == null) {
            if (other.passwordResets != null)
                return false;
        } else if (!passwordResets.equals(other.passwordResets))
            return false;
        if (customerPayments == null) {
            if (other.customerPayments != null)
                return false;
        } else if (!customerPayments.equals(other.customerPayments))
            return false;
        if (billerPayments == null) {
            if (other.billerPayments != null)
                return false;
        } else if (!billerPayments.equals(other.billerPayments))
            return false;
        if (comments == null) {
            if (other.comments != null)
                return false;
        } else if (!comments.equals(other.comments))
            return false;
        if (clothes == null) {
            if (other.clothes != null)
                return false;
        } else if (!clothes.equals(other.clothes))
            return false;
        if (bids == null) {
            if (other.bids != null)
                return false;
        } else if (!bids.equals(other.bids))
            return false;
        if (shippings == null) {
            if (other.shippings != null)
                return false;
        } else if (!shippings.equals(other.shippings))
            return false;
        if (orders == null) {
            if (other.orders != null)
                return false;
        } else if (!orders.equals(other.orders))
            return false;
        if (invoices == null) {
            if (other.invoices != null)
                return false;
        } else if (!invoices.equals(other.invoices))
            return false;
        if (ownerInvoices == null) {
            if (other.ownerInvoices != null)
                return false;
        } else if (!ownerInvoices.equals(other.ownerInvoices))
            return false;
        if (friendRequestees == null) {
            if (other.friendRequestees != null)
                return false;
        } else if (!friendRequestees.equals(other.friendRequestees))
            return false;
        if (friendRequesters == null) {
            if (other.friendRequesters != null)
                return false;
        } else if (!friendRequesters.equals(other.friendRequesters))
            return false;
        if (senders == null) {
            if (other.senders != null)
                return false;
        } else if (!senders.equals(other.senders))
            return false;
        if (receivers == null) {
            if (other.receivers != null)
                return false;
        } else if (!receivers.equals(other.receivers))
            return false;
        if (revieweeReviewers == null) {
            if (other.revieweeReviewers != null)
                return false;
        } else if (!revieweeReviewers.equals(other.revieweeReviewers))
            return false;
        if (reviewerReviewers == null) {
            if (other.reviewerReviewers != null)
                return false;
        } else if (!reviewerReviewers.equals(other.reviewerReviewers))
            return false;
        if (friends == null) {
            if (other.friends != null)
                return false;
        } else if (!friends.equals(other.friends))
            return false;
        if (users == null) {
            if (other.users != null)
                return false;
        } else if (!users.equals(other.users))
            return false;
        if (role != other.role)
            return false;
        return true;
    }

}
