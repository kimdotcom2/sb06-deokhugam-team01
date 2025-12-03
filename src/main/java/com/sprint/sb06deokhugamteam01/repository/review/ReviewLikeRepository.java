package com.sprint.sb06deokhugamteam01.repository.review;

import com.sprint.sb06deokhugamteam01.domain.ReviewLike;
import com.sprint.sb06deokhugamteam01.domain.User;
import com.sprint.sb06deokhugamteam01.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, UUID> {

    Optional<ReviewLike> findByUserAndReview(User user, Review review);

    boolean existsByUserAndReview(User user, Review review);

    void deleteByReview(Review review);
}
