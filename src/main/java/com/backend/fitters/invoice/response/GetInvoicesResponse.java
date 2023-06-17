package com.backend.fitters.invoice.response;

import com.backend.fitters.invoice.dto.InvoicePaginationDto;

public class GetInvoicesResponse {
    private String message;

    private InvoicePaginationDto data;

    public GetInvoicesResponse() {

    }

    public GetInvoicesResponse(String message, InvoicePaginationDto data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public InvoicePaginationDto getData() {
        return data;
    }

    public void setData(InvoicePaginationDto data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
