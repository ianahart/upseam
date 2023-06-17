package com.backend.fitters.payment.response;

public class CreatePaymentResponse {
    private String message;
    private String clientSecret;

    public CreatePaymentResponse() {

    }

    public CreatePaymentResponse(String message, String clientSecret) {
        this.message = message;
        this.clientSecret = clientSecret;
    }

    public String getMessage() {
        return message;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
