package com.example.FORMANTO.dto.user.cart;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CartToWishRequest {
    //0529  카트에서 위시리스트로 넘기는 dto

    private List<Long> productIds;
    private List<Long> customerIds; //List로 안해도 가능한가?


    @Builder
    public CartToWishRequest(List<Long> productIds, List<Long> customerIds) {
        this.productIds = productIds;
        this.customerIds = customerIds;
    }
}
