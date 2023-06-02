package com.backend.fitters.bid;

import com.backend.fitters.bid.request.CreateBidRequest;
import com.backend.fitters.bid.response.AllBidsResponse;
import com.backend.fitters.bid.response.CreateBidResponse;
import com.backend.fitters.bid.response.DeleteBidResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bids")
public class BidController {

    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping
    public ResponseEntity<AllBidsResponse> getBids(
            @RequestParam("clothId") Long clothId,
            @RequestParam("page") int page,
            @RequestParam("direction") String direction,
            @RequestParam("pageSize") int pageSize) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AllBidsResponse(this.bidService.getBids(
                        clothId, page, direction, pageSize), "success"));
    }

    @PostMapping
    public ResponseEntity<CreateBidResponse> createBid(@RequestBody CreateBidRequest request) {
        this.bidService.createBid(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateBidResponse("success"));
    }

    @DeleteMapping("/{bidId}")
    public ResponseEntity<DeleteBidResponse> deleteBid(@PathVariable("bidId") Long bidId) {
        this.bidService.deleteBid(bidId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DeleteBidResponse("success"));
    }
}
