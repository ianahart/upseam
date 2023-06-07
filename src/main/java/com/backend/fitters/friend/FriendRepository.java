package com.backend.fitters.friend;

import java.util.List;
import java.util.Optional;

import com.backend.fitters.friend.dto.CheckIfFriendDto;
import com.backend.fitters.friend.dto.FindFriendDto;

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

    @Query(value = """
            SELECT new com.backend.fitters.friend.dto.FindFriendDto(
              fs.id AS friendObjectId
            ) FROM Friend fs
            INNER JOIN fs.user _u
            INNER JOIN fs.friend _f
            WHERE _u.id = :userId AND _f.id = :friendId
            """)
    FindFriendDto findFriend(@Param("userId") Long userId, @Param("friendId") Long friendId);

    @Query(value = """
            SELECT EXISTS(SELECT 1
            FROM friend fs
            INNER JOIN _user u ON fs.user_id = u.id
            INNER JOIN _user f ON fs.friend_id = f.id
            WHERE u.id = :currentUserId
            AND f.id = :userId
            )
              """, nativeQuery = true)
    boolean checkIfFriends(@Param("currentUserId") Long currentUserId, @Param("userId") Long userId);
}
