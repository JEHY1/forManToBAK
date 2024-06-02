package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<List<Review>> findByUsername(String username);
    Optional<List<Review>> findByProductGroupId(Long productGroupId);
}
