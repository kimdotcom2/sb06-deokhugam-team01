package com.sprint.sb06deokhugamteam01.controller;

import com.sprint.sb06deokhugamteam01.dto.review.*;
import com.sprint.sb06deokhugamteam01.service.review.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewCreateRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewService.createReview(request));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable UUID reviewId,
                                               @RequestHeader UUID requestUserId) {
        return ResponseEntity
                .ok(reviewService.getReview(reviewId, requestUserId));
    }

    @GetMapping
    public ResponseEntity<CursorPageResponseReviewDto> getReviews(
            @RequestParam CursorPageReviewRequest request,
            @RequestHeader UUID requestUserId
    ) {
        return ResponseEntity
                .ok(reviewService.getReviews(request, requestUserId));
    }

    @GetMapping("/popular")
    public ResponseEntity<CursorPageResponsePopularReviewDto> getPopularReviews(
            @RequestParam CursorPagePopularReviewRequest request,
            @RequestHeader UUID requestUserId
    ) {
        return ResponseEntity
                .ok(reviewService.getPopularReviews(request, requestUserId));
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable UUID reviewId,
                                                  @RequestBody ReviewUpdateRequest updateRequest,
                                                  @RequestHeader UUID requestUserId) {
        return ResponseEntity
                .ok(reviewService.updateReview(reviewId, updateRequest, requestUserId));
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewLikeDto> likeReview(@PathVariable UUID reviewId,
                                                    @RequestHeader UUID requestUserId) {
        return ResponseEntity
                .ok(reviewService.likeReview(reviewId, requestUserId));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID reviewId,
                                             @RequestHeader UUID requestUserId) {
        reviewService.deleteReview(reviewId, requestUserId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{reviewId}/hard")
    public ResponseEntity<Void> hardDeleteReview(@PathVariable UUID reviewId,
                                                 @RequestHeader UUID requestUserId) {
        reviewService.hardDeleteReview(reviewId, requestUserId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
    }

}
