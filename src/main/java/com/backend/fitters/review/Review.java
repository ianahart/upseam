package com.backend.fitters.review;

import java.sql.Timestamp;

import com.backend.fitters.cloth.Cloth;
import com.backend.fitters.user.User;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity()
@Table(name = "review")
public class Review {
    @Id
    @SequenceGenerator(name = "review_sequence", sequenceName = "review_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_sequence")
    @Column(name = "id")
    private Long id;

    @CreationTimestamp()
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Min(value = 1)
    @Max(value = 5)
    @Column(name = "rating")
    private Integer rating;

    @Column(name = "text", length = 255)
    private String text;

    @JoinColumn(name = "cloth_id", referencedColumnName = "id")
    @ManyToOne()
    private Cloth cloth;

    @JoinColumn(name = "reviewee_id", referencedColumnName = "id")
    @ManyToOne
    private User reviewee;

    @JoinColumn(name = "reviewer_id", referencedColumnName = "id")
    @ManyToOne
    private User reviewer;

    public Review() {

    }

    public Review(
            Long id,
            Timestamp createdAt,
            Integer rating,
            String text,
            Cloth cloth,
            User reviewee,
            User reviewer) {

        this.id = id;
        this.createdAt = createdAt;
        this.rating = rating;
        this.text = text;
        this.cloth = cloth;
        this.reviewee = reviewee;
        this.reviewer = reviewer;
    }

    public Review(
            Integer rating,
            String text,
            Cloth cloth,
            User reviewee,
            User reviewer) {

        this.rating = rating;
        this.text = text;
        this.cloth = cloth;
        this.reviewee = reviewee;
        this.reviewer = reviewer;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public Integer getRating() {
        return rating;
    }

    public User getReviewee() {
        return reviewee;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setReviewee(User reviewee) {
        this.reviewee = reviewee;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((rating == null) ? 0 : rating.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((cloth == null) ? 0 : cloth.hashCode());
        result = prime * result + ((reviewee == null) ? 0 : reviewee.hashCode());
        result = prime * result + ((reviewer == null) ? 0 : reviewer.hashCode());
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
        Review other = (Review) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (rating == null) {
            if (other.rating != null)
                return false;
        } else if (!rating.equals(other.rating))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (cloth == null) {
            if (other.cloth != null)
                return false;
        } else if (!cloth.equals(other.cloth))
            return false;
        if (reviewee == null) {
            if (other.reviewee != null)
                return false;
        } else if (!reviewee.equals(other.reviewee))
            return false;
        if (reviewer == null) {
            if (other.reviewer != null)
                return false;
        } else if (!reviewer.equals(other.reviewer))
            return false;
        return true;
    }

}
