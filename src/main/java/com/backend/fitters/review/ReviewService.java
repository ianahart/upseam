package com.backend.fitters.review;

import com.backend.fitters.cloth.Cloth;
import com.backend.fitters.cloth.ClothRepository;
import com.backend.fitters.review.dto.CheckExistsDto;
import com.backend.fitters.review.dto.PaginationReviewsDto;
import com.backend.fitters.review.dto.ReviewsDto;
import com.backend.fitters.review.request.CreateReviewRequest;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.advice.BadRequestException;
import com.backend.fitters.user.UserService;
import com.backend.fitters.util.MyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ClothRepository clothRepository;
    private final UserService userService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
            ClothRepository clothRepository, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.clothRepository = clothRepository;
        this.userService = userService;
    }

    public int getUserReviewRating(Long userId) {
        boolean userHasRatings = this.reviewRepository.checkUserHasRatings(userId);

        if (userHasRatings) {
            return this.reviewRepository.getUserReviewRating(userId);
        } else {
            return 0;
        }
    }

    public PaginationReviewsDto getReviews(Long userId, int page, int pageSize, String direction) {
        int currentPage = MyUtils.paginate(page, direction);

        Pageable paging = PageRequest.of(currentPage, pageSize, Sort.by("id"));

        Page<ReviewsDto> results = this.reviewRepository.getReviews(userId, paging);
        System.out.println(results.getTotalPages());

        return new PaginationReviewsDto(results.getContent(),
                results.getTotalPages(),
                currentPage, direction, pageSize);

    }

    public void createReview(CreateReviewRequest request) {
        Cloth cloth = this.clothRepository.findById(request.getClothId())
                .orElseThrow(() -> new NotFoundException("Cloth not found"));
        if (request.getText().isBlank() || request.getText().length() > 255) {
            throw new BadRequestException("Review must be between 1 and 255 characters");
        }

        if (this.reviewRepository.createReview(cloth.getId(), request.getReviewerUserId()) != null) {
            throw new BadRequestException("You can only review a particualar order once");
        }

        this.reviewRepository.save(
                new Review(
                        request.getRating(),
                        request.getText(),
                        cloth,
                        userService.getUserById(request.getRevieweeUserId()),
                        userService.getUserById(request.getReviewerUserId())));

    }
}
