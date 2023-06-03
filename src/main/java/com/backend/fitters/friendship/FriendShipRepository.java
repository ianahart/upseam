package com.backend.fitters.friendship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendShipRepository extends JpaRepository<FriendShip, Long> {

    @Query(value = """
            SELECT EXISTS(SELECT 1
            FROM friendship
            WHERE requester_id = :requester AND requestee_id = :requestee
            )""", nativeQuery = true)
    boolean checkIfFriendRequestSent(@Param("requestee") Long requestee, @Param("requester") Long requester);

}
