package com.sprint.sb06deokhugamteam01.service.review;

import com.sprint.sb06deokhugamteam01.domain.Book;
import com.sprint.sb06deokhugamteam01.domain.Review;
import com.sprint.sb06deokhugamteam01.domain.User;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewCreateRequest;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewDto;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewLikeDto;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewOperationRequest;
import com.sprint.sb06deokhugamteam01.repository.BookRepository;
import com.sprint.sb06deokhugamteam01.repository.ReviewRepository;
import com.sprint.sb06deokhugamteam01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public ReviewDto createReview(ReviewCreateRequest request) {

        User user = userRepository.findById(request.userId()) // TODO 커스텀 예외로 대체
                .orElseThrow(() -> new IllegalArgumentException("해당 정보를 가진 사용자가 존재하지 않습니다."));

        Book book = bookRepository.findById(request.bookId()) // TODO 커스텀 예외로 대체
                .orElseThrow(() -> new IllegalArgumentException("해당 정보를 가진 도서가 존재하지 않습니다."));

        Review review = Review.builder()
                .rating(request.rating())
                .content(request.content())
                .likeCount(0)
                .commentCount(0)
                .isActive(true)
                .user(user)
                .book(book)
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewDto.from(savedReview);
    }

    @Override
    public ReviewDto getReviewById(ReviewOperationRequest request) {
        return null;
    }

    @Override
    public Slice<ReviewDto> getReviews() {
        return null;
    }

    @Override
    public Slice<ReviewDto> getPopularReviews() {
        return null;
    }

    @Override
    public ReviewDto updateReview(ReviewOperationRequest request) {
        return null;
    }

    @Override
    public void deleteReview(ReviewOperationRequest request) {

    }

    @Override
    public void hardDeleteReview(ReviewOperationRequest request) {

    }

    @Override
    public ReviewLikeDto likeReview(ReviewOperationRequest request) {
        return null;
    }
}
