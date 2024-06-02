package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
    Optional<List<Wish>> findAllByCustomerCustomerId(Long customerId);
    Optional<Wish> findByCustomerCustomerIdAndProductGroupProductGroupId(Long customerId, Long productGroupId);
    //wishList 삭제
    Optional<List<Wish>> findAllByWishId(Long wishId);


}