package com.backend.fitters.friend;

import java.util.List;

import com.backend.fitters.friend.dto.CheckIfFriendDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query(value = """
            SELECT new com.backend.fitters.friend.dto.CheckIfFriendDto(
             f.id AS friendId
            ) FROM Friend f
             INNER JOIN f.user _u
             INNER JOIN f.friend _f
            WHERE _u.id = :userOne AND _f.id = :userTwo
                     """)
    CheckIfFriendDto checkIfExistsByFriend(@Param("userOne") Long userOne, @Param("userTwo") Long userTwo);

    @Query(value = """
            SELECT f.id
            FROM friend fs
            INNER JOIN _user u ON fs.user_id = u.id
            INNER JOIN _user f ON fs.friend_id = f.id
            WHERE u.id = :currentUserId
            """, nativeQuery = true)
    Page<Long> getFriendsIds(@Param("currentUserId") Long currentUserId, Pageable pageable);

}
