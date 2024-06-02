package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.ReviewImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewImgRepository extends JpaRepository<ReviewImg, Long> {
    Optional<List<ReviewImg>> findByReviewId(Long reviewId);

}