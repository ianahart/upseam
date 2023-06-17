package com.backend.fitters.invoice;

import com.backend.fitters.invoice.dto.InvoiceDto;
import com.backend.fitters.invoice.dto.InvoiceExistDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = """
            SELECT new com.backend.fitters.invoice.dto.InvoiceExistDto(i.id AS invoiceId)
            FROM Invoice i
            INNER JOIN i.order o
            INNER JOIN i.user u
            WHERE o.id = :orderId
            AND u.id = :userId
            ORDER BY i.id LIMIT 1 """)
    InvoiceExistDto existingInvoice(@Param("userId") Long userId, @Param("orderId") Long orderId);

    @Query(value = """
             SELECT new com.backend.fitters.invoice.dto.InvoiceDto(i.id AS invoiceId,
             p.avatarUrl AS avatarUrl, u.firstName, u.lastName, i.bid, c.clothUrl,
             i.paid, u.id as userId
              )
             FROM Invoice i
             INNER JOIN i.owner o
             INNER JOIN i.user u
             INNER JOIN i.user.profile p
             INNER JOIN i.order.cloth c
             WHERE o.id = :userId
            """)
    Page<InvoiceDto> getInvoices(@Param("userId") Long userId, Pageable pageable);

}
