package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {

    Optional<List<SaleProduct>> findByPaymentId(Long paymentId);

//    Optional<List<SaleProduct>> findAllByPaymentId(Long paymentId);
}
