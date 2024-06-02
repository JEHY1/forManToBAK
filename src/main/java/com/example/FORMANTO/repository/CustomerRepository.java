package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);

    //cart -> wish
    Optional<Customer> findByCustomerId(Long customerId);
}
