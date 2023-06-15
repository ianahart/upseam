package com.backend.fitters.invoice.dto;

public class InvoiceExistDto {
    private Long invoiceId;

    public InvoiceExistDto() {

    }

    public InvoiceExistDto(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
}
