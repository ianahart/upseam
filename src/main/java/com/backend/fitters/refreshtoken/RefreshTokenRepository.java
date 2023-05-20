package com.backend.fitters.refreshtoken;

import java.util.List;
import java.util.Optional;

import com.backend.fitters.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String token);

    @Query(value = """
                SELECT * FROM refresh_token rt
               WHERE rt.user_id =:id
            """, nativeQuery = true)
    List<RefreshToken> findAllUserRefreshTokens(@Param("id") Long id);

    @Modifying
    int deleteByUser(User user);
}
