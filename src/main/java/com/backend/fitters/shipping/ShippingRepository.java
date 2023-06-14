package com.backend.fitters.shipping;

import java.util.List;

import com.backend.fitters.shipping.dto.ShippingDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    @Query(value = """
            SELECT new com.backend.fitters.shipping.dto.ShippingDto(
              s.id as id, u.id as userId, s.zipCode, s.state, s.country, s.address,
              s.firstName, s.lastName, s.shippingType, s.shippingValue
            ) FROM Shipping s
            INNER JOIN s.user u
            WHERE u.id = :userId
            ORDER BY s.id DESC LIMIT 3
                    """)
    List<ShippingDto> limitedShipping(@Param("userId") Long userId);

}
