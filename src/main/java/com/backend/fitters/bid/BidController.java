package com.backend.fitters.bid;

import com.backend.fitters.bid.request.CreateBidRequest;
import com.backend.fitters.bid.response.CreateBidResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bids")
public class BidController {

    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    public ResponseEntity<CreateBidResponse> createBid(@RequestBody CreateBidRequest request) {
        this.bidService.createBid(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateBidResponse("success"));
    }
}
