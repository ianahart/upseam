package com.backend.fitters.profile.response;

import com.backend.fitters.profile.dto.ProfileShippingDto;

public class GetProfileShippingResponse {
    private String message;
    private ProfileShippingDto shipping;

    public GetProfileShippingResponse() {

    }

    public GetProfileShippingResponse(String message, ProfileShippingDto shipping) {
        this.message = message;
        this.shipping = shipping;

    }

    public ProfileShippingDto getShipping() {
        return shipping;
    }

    public String getMessage() {
        return message;
    }

    public void setShipping(ProfileShippingDto shipping) {
        this.shipping = shipping;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
