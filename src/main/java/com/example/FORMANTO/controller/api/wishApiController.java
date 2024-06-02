package com.example.FORMANTO.controller.api;

import com.example.FORMANTO.domain.Wish;
import com.example.FORMANTO.dto.ChangeWishRequest;
import com.example.FORMANTO.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class wishApiController {

    private final WishListService wishListService;
    @PostMapping("/api/wish")
    public ResponseEntity<Wish> changeWish(Principal principal, @RequestBody ChangeWishRequest request){
        System.err.println("called wish");
        return ResponseEntity.ok()
                        .body(wishListService.change(principal, request.getProductGroupId()));
    }
}
