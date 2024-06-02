package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Customer;
import com.example.FORMANTO.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findByUsername(String username){
        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("not found user"));
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
}
