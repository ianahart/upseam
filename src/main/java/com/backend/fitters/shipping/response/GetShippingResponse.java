package com.backend.fitters.shipping.response;

import java.util.List;

import com.backend.fitters.shipping.dto.ShippingDto;

public class GetShippingResponse {
    private String message;
    private List<ShippingDto> shipping;

    public GetShippingResponse() {

    }

    public GetShippingResponse(String message, List<ShippingDto> shipping) {
        this.message = message;
        this.shipping = shipping;
    }

    public String getMessage() {
        return message;
    }

    public List<ShippingDto> getShipping() {
        return shipping;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setShipping(List<ShippingDto> shipping) {
        this.shipping = shipping;
    }
}
