package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<List<Cart>> findByCustomerId(Long customerId);

    Optional<Cart> findByCustomerIdAndProductId(Long customerId, Long productId);

    Optional <List<Cart>> findAllByCartId(Long cartId);
}
