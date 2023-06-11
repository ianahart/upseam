package com.backend.fitters.subscriber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    @Query(value = """
             SELECT EXISTS(SELECT 1
             FROM subscriber s
             WHERE s.email = :email)
            """, nativeQuery = true)

    boolean checkEmailExists(@Param("email") String email);

}
