package com.example.FORMANTO.controller.api;

import com.example.FORMANTO.domain.Payment;
import com.example.FORMANTO.dto.order.PaymentRequest;
import com.example.FORMANTO.dto.order.PaymentResponse;
import com.example.FORMANTO.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final PaymentService paymentService;

    @PostMapping("/api/payment")
    public ResponseEntity<PaymentResponse> payment(@RequestBody PaymentRequest request, Principal principal){

        System.err.println("call paymentApi");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.searchQuantity(request, principal));
    }
}
