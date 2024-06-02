package com.example.FORMANTO.controller.api;

import com.example.FORMANTO.domain.Customer;
import com.example.FORMANTO.dto.signup.JoinRequest;
import com.example.FORMANTO.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinApiController {

    private final CustomerService customerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //미완성
    @PostMapping("/user")
    public ResponseEntity<Customer> user(@RequestBody JoinRequest request){

        if(request.getAdminKey() == null){
            System.err.println("admin key is null");
        }

        Customer customer = Customer.builder()
                .username(request.getUsername())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phoneNum(request.getPhoneNum())
                .adminKey(request.getAdminKey())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.save(customer));
    }
}
