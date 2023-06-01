package com.backend.fitters.bid;

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
}
