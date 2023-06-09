package com.backend.fitters.user;

import java.util.List;
import java.util.Optional;

import com.backend.fitters.user.dto.GetFriendsDto;
import com.backend.fitters.user.dto.GetUserSimpleProfileDto;
import com.backend.fitters.user.dto.GetUsersDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = """
            SELECT new com.backend.fitters.user.dto.GetUsersDto(
             u.role, u.id, u.firstName, u.lastName,
              _p.id, _p.avatarUrl
            ) FROM User u
            INNER JOIN u.profile _p
            WHERE LOWER(u.firstName) LIKE %:term% OR LOWER(u.lastName) LIKE %:term%
                    """)
    Page<GetUsersDto> searchUsers(@Param("term") String term, Pageable paging);

    @Query(value = """
            SELECT DISTINCT new com.backend.fitters.user.dto.GetFriendsDto(
               u.id AS userId,
               u.firstName AS firstName,
              u.lastName AS lastName,
              p.id AS profileId,
              p.avatarUrl AS avatarUrl
            ) FROM User u
              INNER JOIN u.profile p
              WHERE u.id IN (:friendsIds)
              ORDER BY u.id
                    """)
    List<GetFriendsDto> getFriends(@Param("friendsIds") List<Long> friendsIds);

    @Query(value = """
              SELECT new com.backend.fitters.user.dto.GetUserSimpleProfileDto(
              u.id, u.email, p.avatarUrl, p.address, p.country,
             p.zipCode, p.state,
             u.firstName, u.lastName
              ) FROM User u
              INNER JOIN u.profile p
              WHERE u.id = :userId
            """)
    GetUserSimpleProfileDto getUserSimpleProfile(@Param("userId") Long userId);
}
