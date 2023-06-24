package com.backend.fitters.review;

import com.backend.fitters.review.dto.CheckExistsDto;
import com.backend.fitters.review.dto.ReviewsDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = """
             SELECT EXISTS(SELECT 1
             FROM review r
             INNER JOIN _user u ON r.reviewee_id = u.id
             WHERE u.id = :userId)
            """, nativeQuery = true)
    boolean checkUserHasRatings(@Param("userId") Long userId);

    @Query(value = """
            SELECT AVG(r.rating)
            FROM Review r
            INNER JOIN r.reviewee _r
            WHERE _r.id = :userId
             """)
    Integer getUserReviewRating(@Param("userId") Long userId);

    @Query(value = """
            SELECT new com.backend.fitters.review.dto.CheckExistsDto(r.id AS reviewId)
             FROM Review r
             INNER JOIN r.cloth c
             INNER JOIN r.reviewer _r
             WHERE c.id = :clothId
             AND _r.id = :reviewerId
             ORDER BY r.id LIMIT 1
            """)
    CheckExistsDto createReview(@Param("clothId") Long clothId, @Param("reviewerId") Long reviewerId);

    @Query(value = """
            SELECT new com.backend.fitters.review.dto.ReviewsDto(
              r.id AS reviewId, r.text AS text, r.rating AS rating,
              _r.firstName AS firstName, _r.lastName AS lastName, r.createdAt AS createdAt,
             _rp.avatarUrl AS avatarUrl
            )
                      FROM Review r
                      INNER JOIN r.reviewee re
                      INNER JOIN r.reviewer.profile _rp
                      INNER JOIN r.reviewer _r
                      WHERE re.id = :userId
                    """)
    Page<ReviewsDto> getReviews(@Param("userId") Long userId, Pageable pageable);

}
