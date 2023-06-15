package com.backend.fitters.order;

import com.backend.fitters.order.dto.OrderDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
            SELECT new com.backend.fitters.order.dto.OrderDto(
              o.id, c.id AS clothId, c.clothUrl, r.firstName, r.lastName,
              p.avatarUrl, c.dueDate, o.complete, o.bid
            ) FROM Order o
            INNER JOIN o.receiverUser r
            INNER JOIN o.receiverUser.profile p
            INNER JOIN o.bidUser b
            INNER JOIN o.cloth c
            WHERE b.id = :userId
                        """)
    Page<OrderDto> getOrders(@Param("userId") Long userId, Pageable pageable);
}
