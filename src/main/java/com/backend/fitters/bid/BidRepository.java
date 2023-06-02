package com.backend.fitters.bid;

import com.backend.fitters.bid.dto.BidDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query(value = """
             SELECT EXISTS(SELECT 1
             FROM bid
             WHERE user_id = :userId
             AND cloth_id = :clothId)
            """, nativeQuery = true)
    boolean findByIdWithUserAndCloth(@Param("userId") Long userId, @Param("clothId") Long clothId);

    @Query(value = """
            SELECT new com.backend.fitters.bid.dto.BidDto(
              b.id, b.createdAt, b.bid, u.firstName,
              u.lastName, p.avatarUrl, p.id AS profileId, u.id AS userId
            ) FROM Bid b INNER JOIN b.cloth c
            INNER JOIN b.user u
            INNER JOIN b.user.profile p
            WHERE c.id = :clothId""")
    Page<BidDto> findAllBidsByClothId(@Param("clothId") Long clothId, Pageable pageable);

}
