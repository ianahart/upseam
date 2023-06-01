package com.backend.fitters.bid.dto;

import org.springframework.data.domain.Page;

public class BidsWithPaginationDto {

    private Page<BidDto> bids;
    private int page;
    private String direction;

    public BidsWithPaginationDto() {

    }

    public BidsWithPaginationDto(Page<BidDto> bids, int page, String direction) {
        this.bids = bids;
        this.page = page;
        this.direction = direction;
    }

    public int getPage() {
        return page;
    }

    public String getDirection() {
        return direction;
    }

    public Page<BidDto> getBids() {
        return bids;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setBids(Page<BidDto> bids) {
        this.bids = bids;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
