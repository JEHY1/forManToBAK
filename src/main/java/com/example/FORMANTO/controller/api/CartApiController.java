package com.example.FORMANTO.controller.api;

import com.example.FORMANTO.domain.Cart;
import com.example.FORMANTO.dto.productDetail.AddCartRequest;
import com.example.FORMANTO.service.CartService;
import com.example.FORMANTO.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;
    private final CustomerService customerService;
    @PostMapping("/api/cart")
    public ResponseEntity<List<Cart>> addCart(@RequestBody AddCartRequest request, Principal principal){

        List<Cart> carts =  cartService.addCart(request, customerService.findByUsername(principal.getName()).getCustomerId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carts);
    }
}
