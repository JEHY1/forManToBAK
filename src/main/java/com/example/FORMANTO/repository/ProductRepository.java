package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findByProductGroupId(Long productGroupId);
    Optional<List<Product>> findAllByProductIdIn(List<Long> productIds); //select * from product_tb where product_id == productIds.index(index++) and productIds.index(index++) ...
    //productIds 에 해당하는 모든 값을 product_id 에서 검색 후 리턴
    Optional<Product> findByProductId(Long productId);

}
