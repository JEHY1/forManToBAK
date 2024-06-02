package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {

    Optional<ProductGroup> findByProductGroupId(Long ProductGroupId);

    Optional<List<ProductGroup>> findAllByProductGroupId(Long productGroupId);

}
