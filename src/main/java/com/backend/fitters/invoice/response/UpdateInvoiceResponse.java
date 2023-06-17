package com.backend.fitters.invoice.response;

public class UpdateInvoiceResponse {
    private String message;
    private Boolean paid;

    public UpdateInvoiceResponse() {

    }

    public UpdateInvoiceResponse(String message, Boolean paid) {
        this.message = message;
        this.paid = paid;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
