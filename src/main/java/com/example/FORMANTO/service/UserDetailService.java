package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Customer;
import com.example.FORMANTO.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Override
    public Customer loadUserByUsername(String username){

        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("not found user"));
    }
}
