package com.backend.fitters.invoice.response;

public class CreateInvoiceResponse {
    private String message;

    public CreateInvoiceResponse() {

    }

    public CreateInvoiceResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
