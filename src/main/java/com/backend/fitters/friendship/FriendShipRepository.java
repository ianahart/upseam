package com.backend.fitters.friendship;

import java.util.List;
import com.backend.fitters.friendship.dto.GetFriendShipsDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
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

    @Query(value = """
            SELECT new com.backend.fitters.friendship.dto.GetFriendShipsDto(
            fs.id, r.id AS requesteeId, _r.id AS requesterId, _r.firstName,
            _r.lastName, p.id AS profileId, p.avatarUrl
             ) FROM FriendShip fs
            INNER JOIN fs.requestee r
            INNER JOIN fs.requester _r
            INNER JOIN fs.requester.profile p
            WHERE r.id = :requesteeId
            AND fs.pending = true
             """)
    Page<GetFriendShipsDto> getFriendRequests(@Param("requesteeId") Long requesteeId, Pageable pageable);
}
