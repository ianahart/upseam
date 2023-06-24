package com.backend.fitters.review;

import com.backend.fitters.review.request.CreateReviewRequest;
import com.backend.fitters.review.response.CreateReviewResponse;
import com.backend.fitters.review.response.GetReviewsResponse;
import com.backend.fitters.review.response.GetUserReviewRatingResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/user")
    public ResponseEntity<GetUserReviewRatingResponse> getUserReviewRating(@RequestParam("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new GetUserReviewRatingResponse("success", this.reviewService.getUserReviewRating(userId)));
    }

    @GetMapping
    public ResponseEntity<GetReviewsResponse> getReviews(
            @RequestParam("userId") Long userId,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("direction") String direction) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new GetReviewsResponse("success", this.reviewService.getReviews(userId, page, pageSize, direction)));
    }

    @PostMapping
    public ResponseEntity<CreateReviewResponse> createReview(@RequestBody CreateReviewRequest request) {
        this.reviewService.createReview(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateReviewResponse("success"));
    }
}
