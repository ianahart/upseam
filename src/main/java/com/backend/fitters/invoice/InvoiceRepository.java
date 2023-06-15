package com.backend.fitters.invoice;

import com.backend.fitters.invoice.dto.InvoiceExistDto;

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

}
