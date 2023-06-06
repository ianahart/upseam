package com.backend.fitters.user;

import java.util.List;
import java.util.Optional;

import com.backend.fitters.user.dto.GetFriendsDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

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

}
