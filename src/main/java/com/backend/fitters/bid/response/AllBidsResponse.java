package com.backend.fitters.bid.response;

import com.backend.fitters.bid.dto.BidDto;
import com.backend.fitters.bid.dto.BidsWithPaginationDto;

import org.springframework.data.domain.Page;

public class AllBidsResponse {
    private BidsWithPaginationDto data;
    private String message;

    public AllBidsResponse() {

    }

    public AllBidsResponse(BidsWithPaginationDto data, String message) {
        this.data = data;
        this.message = message;
    }

    public BidsWithPaginationDto getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setBids(BidsWithPaginationDto data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
