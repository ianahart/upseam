package com.backend.fitters.profile;

import com.backend.fitters.profile.dto.ProfileFieldsDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = """
            SELECT p.zip_code AS zipCode, p.country,
             p.address, p.pricing, p.site, p.bio,
            p.state, p.specialities, p.avatar_url as avatarUrl
             FROM profile p
             WHERE p.id = :profileId
            """, nativeQuery = true)
    ProfileFieldsDto getProfileFields(@Param("profileId") Long profileId);
}
