package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    //삭제할지도
    Optional<List<Payment>> findByCustomerIdAndPaymentId(Long customerId, Long paymentId);

    Optional<List<Payment>>findByCustomerId(Long customerId);
}
